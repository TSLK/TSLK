/*******************************************************************************
 * Copyright (c) 2013 Nick Guletskii.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Nick Guletskii - initial API and implementation
 ******************************************************************************/
package org.ng200.tslk.lang.runtime;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedHashMap;

import org.ng200.tslk.lang.runtime.TSLKObject.Type;
import org.ng200.tslk.lang.runtime.exceptions.TSLKRuntimeException;
import org.ng200.tslk.lang.runtime.exceptions.TSLKTypeMismatchException;

public class TSLKBinaryOperatorService {
	@SuppressWarnings("rawtypes")
	private final BinaryOperator operators[];
	private final TSLKInstance instance;

	private enum Operator {
		ADD, AND, DIVIDE, EQUALS, EXPONENTIATE, ISLESS, ISLESSOREQUAL, ISMORE, ISMOREOREQUAL, MULTIPLY, REMAINDER, SUBTRACT, OR
	}

	private int index(Operator op, Type l, Type r) {
		return op.ordinal() * Type.values().length * Type.values().length
				+ l.ordinal() * Type.values().length + r.ordinal();
	}

	public TSLKBinaryOperatorService(TSLKInstance parentTSLKInstance) {
		this.instance = parentTSLKInstance;
		{
			this.operators = new BinaryOperator[Operator.values().length
					* Type.values().length * Type.values().length];
			for (final Operator op : Operator.values())
				for (Type tl : Type.values())
					for (Type tr : Type.values())
						this.operators[index(op, tl, tr)] = new BinaryOperator<TSLKObject, TSLKObject>() {
							@Override
							public TSLKObject getResult(TSLKObject l,
									TSLKObject r) {
								throw new TSLKTypeMismatchException(
										"Couldn't perform '"
												+ op.toString().toLowerCase()
												+ "' on "
												+ (l == null ? "null"
														: l.getType())
												+ " and "
												+ (r == null ? "null"
														: r.getType()));
							}
						};
			// Concatenation
			for (Type tl : Type.values()) {
				for (Type tr : Type.values()) {
					this.operators[index(Operator.ADD, tl, tr)] = new BinaryOperator<TSLKObject, TSLKObject>() {
						@Override
						public TSLKObject getResult(TSLKObject l, TSLKObject r) {
							return new TSLKString(instance,
									((l == null) ? "null" : l.toString())
											+ ((r == null) ? "null" : r
													.toString()));
						}
					};
				}
			}
			// Self explanatory
			this.operators[index(Operator.ADD, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKNumber(instance, l.getValue().add(
							r.getValue()));
				}
			};
			this.operators[index(Operator.ADD, Type.NUMBER, Type.BOOLEAN)] = new BinaryOperator<TSLKNumber, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKBoolean r) {
					return new TSLKNumber(instance, l.getValue().add(
							r.getValue() ? BigDecimal.ONE : BigDecimal.ZERO));
				}
			};
			registerReverseOperator(Operator.ADD, Type.NUMBER, Type.BOOLEAN);
			this.operators[index(Operator.ADD, Type.BOOLEAN, Type.BOOLEAN)] = new BinaryOperator<TSLKBoolean, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKBoolean l, TSLKBoolean r) {
					return new TSLKNumber(instance,
							(l.getValue() ? BigDecimal.ONE : BigDecimal.ZERO)
									.add(l.getValue() ? BigDecimal.ONE
											: BigDecimal.ZERO));
				}
			};
			this.operators[index(Operator.ADD, Type.TABLE, Type.TABLE)] = new BinaryOperator<TSLKTable, TSLKTable>() {
				@Override
				public TSLKObject getResult(TSLKTable l, TSLKTable r) {
					LinkedHashMap<Integer, TSLKObject> union = new LinkedHashMap<Integer, TSLKObject>(
							l.getChildren());
					union.putAll(r.getChildren());
					return new TSLKTable(instance, union);
				}
			};
		}
		{
			this.operators[index(Operator.SUBTRACT, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKNumber(instance, l.getValue().subtract(
							r.getValue()));
				}
			};
			this.operators[index(Operator.SUBTRACT, Type.NUMBER, Type.BOOLEAN)] = new BinaryOperator<TSLKNumber, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKBoolean r) {
					return new TSLKNumber(instance, l.getValue().subtract(
							r.getValue() ? BigDecimal.ONE : BigDecimal.ZERO));
				}
			};
			this.operators[index(Operator.SUBTRACT, Type.BOOLEAN, Type.NUMBER)] = new BinaryOperator<TSLKBoolean, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKBoolean l, TSLKNumber r) {
					return new TSLKNumber(instance,
							(l.getValue() ? BigDecimal.ONE : BigDecimal.ZERO)
									.subtract(r.getValue()));
				}
			};
			this.operators[index(Operator.SUBTRACT, Type.BOOLEAN, Type.BOOLEAN)] = new BinaryOperator<TSLKBoolean, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKBoolean l, TSLKBoolean r) {
					return new TSLKNumber(instance,
							(l.getValue() ? BigDecimal.ONE : BigDecimal.ZERO)
									.subtract(l.getValue() ? BigDecimal.ONE
											: BigDecimal.ZERO));
				}
			};
			this.operators[index(Operator.SUBTRACT, Type.TABLE, Type.TABLE)] = new BinaryOperator<TSLKTable, TSLKTable>() {
				@Override
				public TSLKObject getResult(TSLKTable l, TSLKTable r) {
					LinkedHashMap<Integer, TSLKObject> subtract = new LinkedHashMap<Integer, TSLKObject>(
							l.getChildren());
					for (Integer i : r.getChildren().keySet())
						if (subtract.containsKey(i))
							subtract.remove(i);
					return new TSLKTable(instance, subtract);
				}
			};
		}
		{
			this.operators[index(Operator.MULTIPLY, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKNumber(instance, l.getValue().multiply(
							r.getValue()));
				}
			};
			this.operators[index(Operator.MULTIPLY, Type.NUMBER, Type.BOOLEAN)] = new BinaryOperator<TSLKNumber, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKBoolean r) {
					return new TSLKNumber(instance, l.getValue().multiply(
							r.getValue() ? BigDecimal.ONE : BigDecimal.ZERO));
				}
			};
			registerReverseOperator(Operator.MULTIPLY, Type.NUMBER,
					Type.BOOLEAN);
			this.operators[index(Operator.MULTIPLY, Type.STRING, Type.NUMBER)] = new BinaryOperator<TSLKString, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKString l, TSLKNumber r) {
					if (r.getValue().compareTo(BigDecimal.ZERO) < 0
							|| !TSLKNumber.isWhole(r.getValue()))
						throw new TSLKRuntimeException(
								"You can only repeat the string n times, where n is a non-negative integer!");
					StringBuilder stringBuilder = new StringBuilder();
					for (int i = 0; i < r.getValue().intValue(); i++)
						stringBuilder.append(l.toString());
					return new TSLKString(instance, stringBuilder.toString());
				}
			};
		}
		{
			this.operators[index(Operator.DIVIDE, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					if (r.getValue().compareTo(BigDecimal.ZERO) == 0)
						throw new TSLKRuntimeException("Can't divide by zero!");
					return new TSLKNumber(instance, l.getValue().divide(
							r.getValue(), 16, RoundingMode.HALF_UP));
				}
			};
			this.operators[index(Operator.DIVIDE, Type.BOOLEAN, Type.NUMBER)] = new BinaryOperator<TSLKBoolean, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKBoolean l, TSLKNumber r) {
					if (r.getValue().compareTo(BigDecimal.ONE) == 0)
						throw new TSLKRuntimeException("Can't divide by zero!");
					return new TSLKNumber(instance,
							(l.getValue() ? BigDecimal.ONE : BigDecimal.ZERO)
									.divide(r.getValue()));
				}
			};
		}
		{
			this.operators[index(Operator.REMAINDER, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					if (r.getValue().compareTo(BigDecimal.ZERO) == 0)
						throw new TSLKRuntimeException("Can't divide by zero!");
					return new TSLKNumber(instance, l.getValue().remainder(
							r.getValue(), TSLKNumber.DEFAULT));
				}
			};
		}
		{
			this.operators[index(Operator.AND, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					if (!TSLKNumber.isWhole(l.getValue())
							|| !TSLKNumber.isWhole(r.getValue()))
						throw new TSLKRuntimeException(
								"Bitwise operations can only be done on whole numbers and booleans!");
					return new TSLKNumber(instance, new BigDecimal(l.getValue()
							.longValue() & r.getValue().longValue()));
				}
			};
			this.operators[index(Operator.AND, Type.NUMBER, Type.BOOLEAN)] = new BinaryOperator<TSLKNumber, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKBoolean r) {
					return new TSLKBoolean(instance, l.getValue().compareTo(
							BigDecimal.ZERO) != 0
							&& r.getValue());
				}
			};
			registerReverseOperator(Operator.AND, Type.NUMBER, Type.BOOLEAN);
			this.operators[index(Operator.AND, Type.BOOLEAN, Type.BOOLEAN)] = new BinaryOperator<TSLKBoolean, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKBoolean l, TSLKBoolean r) {
					return new TSLKBoolean(instance,
							(l.getValue() && r.getValue()));
				}
			};
		}
		{
			this.operators[index(Operator.OR, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					if (!TSLKNumber.isWhole(l.getValue())
							|| !TSLKNumber.isWhole(r.getValue()))
						throw new TSLKRuntimeException(
								"Bitwise operations can only be done on whole numbers and booleans!");
					return new TSLKNumber(instance, new BigDecimal(l.getValue()
							.longValue() | r.getValue().longValue()));
				}
			};
			this.operators[index(Operator.OR, Type.NUMBER, Type.BOOLEAN)] = new BinaryOperator<TSLKNumber, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKBoolean r) {
					return new TSLKBoolean(instance, l.getValue().compareTo(
							BigDecimal.ZERO) != 0
							|| r.getValue());
				}
			};
			registerReverseOperator(Operator.OR, Type.NUMBER, Type.BOOLEAN);
			this.operators[index(Operator.OR, Type.BOOLEAN, Type.BOOLEAN)] = new BinaryOperator<TSLKBoolean, TSLKBoolean>() {
				@Override
				public TSLKObject getResult(TSLKBoolean l, TSLKBoolean r) {
					return new TSLKBoolean(instance,
							(l.getValue() || r.getValue()));
				}
			};
		}
		{
			this.operators[index(Operator.ISLESS, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKBoolean(instance, (l.getValue().compareTo(
							r.getValue()) < 0));
				}
			};
		}
		{
			this.operators[index(Operator.ISLESSOREQUAL, Type.NUMBER,
					Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKBoolean(instance, (l.getValue().compareTo(
							r.getValue()) <= 0));
				}
			};
		}
		{
			this.operators[index(Operator.ISMORE, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKBoolean(instance, (l.getValue().compareTo(
							r.getValue()) > 0));
				}
			};
		}
		{
			this.operators[index(Operator.ISMOREOREQUAL, Type.NUMBER,
					Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKBoolean(instance, (l.getValue().compareTo(
							r.getValue()) >= 0));
				}
			};
		}
		{
			this.operators[index(Operator.EQUALS, Type.NUMBER, Type.NUMBER)] = new BinaryOperator<TSLKNumber, TSLKNumber>() {
				@Override
				public TSLKObject getResult(TSLKNumber l, TSLKNumber r) {
					return new TSLKBoolean(instance, (l.getValue().compareTo(
							r.getValue()) == 0));
				}
			};
		}
	}

	public TSLKObject add(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.ADD, l, r)].getResultRaw(l, r);
	}

	public TSLKObject and(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.AND, l, r)].getResultRaw(l, r);
	}

	public TSLKObject divide(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.DIVIDE, l, r)].getResultRaw(l, r);
	}

	public TSLKObject exponentiate(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.EXPONENTIATE, l, r)].getResultRaw(
				l, r);
	}

	public TSLKBoolean isLess(TSLKObject l, TSLKObject r) {
		TSLKObject obj = this.operators[index(Operator.ISLESS, l, r)]
				.getResultRaw(l, r);
		if (obj.getType() != Type.BOOLEAN)
			throw new TSLKTypeMismatchException(
					"The operator less has returned a non-boolean value!");
		return (TSLKBoolean) obj;
	}

	public TSLKBoolean isLessOrEqual(TSLKObject l, TSLKObject r) {
		TSLKObject obj = this.operators[index(Operator.ISLESSOREQUAL, l, r)]
				.getResultRaw(l, r);
		if (obj.getType() != Type.BOOLEAN)
			throw new TSLKTypeMismatchException(
					"The operator less or equal has returned a non-boolean value!");
		return (TSLKBoolean) obj;
	}

	public TSLKBoolean isMore(TSLKObject l, TSLKObject r) {
		TSLKObject obj = this.operators[index(Operator.ISMORE, l, r)]
				.getResultRaw(l, r);
		if (obj.getType() != Type.BOOLEAN)
			throw new TSLKTypeMismatchException(
					"The operator more has returned a non-boolean value!");
		return (TSLKBoolean) obj;
	}

	public TSLKBoolean isMoreOrEqual(TSLKObject l, TSLKObject r) {
		TSLKObject obj = this.operators[index(Operator.ISMOREOREQUAL, l, r)]
				.getResultRaw(l, r);
		if (obj.getType() != Type.BOOLEAN)
			throw new TSLKTypeMismatchException(
					"The operator more or equal has returned a non-boolean value!");
		return (TSLKBoolean) obj;
	}

	public TSLKObject multiply(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.MULTIPLY, l, r)]
				.getResultRaw(l, r);
	}

	public TSLKObject or(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.OR, l, r)].getResultRaw(l, r);
	}

	public TSLKObject remainder(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.REMAINDER, l, r)].getResultRaw(l,
				r);
	}

	public TSLKObject subtract(TSLKObject l, TSLKObject r) {
		return this.operators[index(Operator.SUBTRACT, l, r)]
				.getResultRaw(l, r);
	}

	private int index(Operator op, TSLKObject l, TSLKObject r) {
		return index(op, l == null ? Type.NULL : l.getType(),
				r == null ? Type.NULL : r.getType());
	}

	@SuppressWarnings("unchecked")
	private void registerReverseOperator(Operator op, Type l, Type r) {
		operators[index(op, r, l)] = new ReverseBinaryOperator(operators[index(
				op, l, r)]);
	}

	public static abstract class BinaryOperator<L extends TSLKObject, R extends TSLKObject> {
		public abstract TSLKObject getResult(L l, R r);

		@SuppressWarnings("unchecked")
		public TSLKObject getResultRaw(TSLKObject l, TSLKObject r) {
			return getResult((L) l, (R) r);
		}
	}

	public static class ReverseBinaryOperator extends
			BinaryOperator<TSLKObject, TSLKObject> {
		private BinaryOperator<TSLKObject, TSLKObject> operator;

		public ReverseBinaryOperator(
				BinaryOperator<TSLKObject, TSLKObject> operator) {
			this.operator = operator;
		}

		@Override
		public TSLKObject getResult(TSLKObject l, TSLKObject r) {
			return operator.getResultRaw(r, l);
		}
	}

	public TSLKBoolean isEqual(TSLKObject l, TSLKObject r) {
		TSLKObject obj = this.operators[index(Operator.ISMOREOREQUAL, l, r)]
				.getResultRaw(l, r);
		if (obj.getType() != Type.BOOLEAN)
			throw new TSLKTypeMismatchException(
					"The operator equal has returned a non-boolean value!");
		return (TSLKBoolean) obj;
	}
}
