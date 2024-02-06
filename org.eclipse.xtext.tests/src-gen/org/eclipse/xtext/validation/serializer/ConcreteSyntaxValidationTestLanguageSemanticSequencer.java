/*******************************************************************************
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.validation.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.eclipse.xtext.validation.csvalidationtest.AltList1;
import org.eclipse.xtext.validation.csvalidationtest.AltList2;
import org.eclipse.xtext.validation.csvalidationtest.AlternativeMultiplicities;
import org.eclipse.xtext.validation.csvalidationtest.AssignedAction;
import org.eclipse.xtext.validation.csvalidationtest.Combination1;
import org.eclipse.xtext.validation.csvalidationtest.Combination2;
import org.eclipse.xtext.validation.csvalidationtest.Combination3;
import org.eclipse.xtext.validation.csvalidationtest.Combination4;
import org.eclipse.xtext.validation.csvalidationtest.CsvalidationtestPackage;
import org.eclipse.xtext.validation.csvalidationtest.EmptyAlternativeSub;
import org.eclipse.xtext.validation.csvalidationtest.GroupMultiplicities;
import org.eclipse.xtext.validation.csvalidationtest.Heuristic1;
import org.eclipse.xtext.validation.csvalidationtest.List1;
import org.eclipse.xtext.validation.csvalidationtest.List2;
import org.eclipse.xtext.validation.csvalidationtest.List3;
import org.eclipse.xtext.validation.csvalidationtest.List4;
import org.eclipse.xtext.validation.csvalidationtest.List5;
import org.eclipse.xtext.validation.csvalidationtest.Model;
import org.eclipse.xtext.validation.csvalidationtest.SimpleAlternative;
import org.eclipse.xtext.validation.csvalidationtest.SimpleGroup;
import org.eclipse.xtext.validation.csvalidationtest.SimpleMultiplicities;
import org.eclipse.xtext.validation.csvalidationtest.StaticSimplification;
import org.eclipse.xtext.validation.csvalidationtest.TransientObject;
import org.eclipse.xtext.validation.csvalidationtest.TransientObjectSub;
import org.eclipse.xtext.validation.csvalidationtest.TransientSerializeables1;
import org.eclipse.xtext.validation.csvalidationtest.TwoVersion;
import org.eclipse.xtext.validation.csvalidationtest.UnassignedAction1;
import org.eclipse.xtext.validation.csvalidationtest.UnassignedAction2Sub;
import org.eclipse.xtext.validation.csvalidationtest.UnassignedAction2Sub1;
import org.eclipse.xtext.validation.csvalidationtest.UnassignedAction2Sub2;
import org.eclipse.xtext.validation.csvalidationtest.UnassignedRuleCall1Sub;
import org.eclipse.xtext.validation.csvalidationtest.UnassignedRuleCall2SubAction;
import org.eclipse.xtext.validation.services.ConcreteSyntaxValidationTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class ConcreteSyntaxValidationTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private ConcreteSyntaxValidationTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == CsvalidationtestPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case CsvalidationtestPackage.ALT_LIST1:
				sequence_AltList1(context, (AltList1) semanticObject); 
				return; 
			case CsvalidationtestPackage.ALT_LIST2:
				sequence_AltList2(context, (AltList2) semanticObject); 
				return; 
			case CsvalidationtestPackage.ALTERNATIVE_MULTIPLICITIES:
				sequence_AlternativeMultiplicities(context, (AlternativeMultiplicities) semanticObject); 
				return; 
			case CsvalidationtestPackage.ASSIGNED_ACTION:
				if (rule == grammarAccess.getAssignedActionSecondRule()) {
					sequence_AssignedActionSecond(context, (AssignedAction) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getAssignedActionRule()) {
					sequence_AssignedAction(context, (AssignedAction) semanticObject); 
					return; 
				}
				else if (action == grammarAccess.getAssignedActionAccess().getAssignedActionChildAction_2_0()) {
					sequence_AssignedAction_AssignedAction_2_0(context, (AssignedAction) semanticObject); 
					return; 
				}
				else break;
			case CsvalidationtestPackage.COMBINATION1:
				sequence_Combination1(context, (Combination1) semanticObject); 
				return; 
			case CsvalidationtestPackage.COMBINATION2:
				sequence_Combination2(context, (Combination2) semanticObject); 
				return; 
			case CsvalidationtestPackage.COMBINATION3:
				sequence_Combination3(context, (Combination3) semanticObject); 
				return; 
			case CsvalidationtestPackage.COMBINATION4:
				sequence_Combination4(context, (Combination4) semanticObject); 
				return; 
			case CsvalidationtestPackage.EMPTY_ALTERNATIVE_SUB:
				sequence_StaticSimplification(context, (EmptyAlternativeSub) semanticObject); 
				return; 
			case CsvalidationtestPackage.GROUP_MULTIPLICITIES:
				sequence_GroupMultiplicities(context, (GroupMultiplicities) semanticObject); 
				return; 
			case CsvalidationtestPackage.HEURISTIC1:
				sequence_Heuristic1(context, (Heuristic1) semanticObject); 
				return; 
			case CsvalidationtestPackage.LIST1:
				sequence_List1(context, (List1) semanticObject); 
				return; 
			case CsvalidationtestPackage.LIST2:
				sequence_List2(context, (List2) semanticObject); 
				return; 
			case CsvalidationtestPackage.LIST3:
				sequence_List3(context, (List3) semanticObject); 
				return; 
			case CsvalidationtestPackage.LIST4:
				sequence_List4(context, (List4) semanticObject); 
				return; 
			case CsvalidationtestPackage.LIST5:
				sequence_List5(context, (List5) semanticObject); 
				return; 
			case CsvalidationtestPackage.MODEL:
				sequence_Model(context, (Model) semanticObject); 
				return; 
			case CsvalidationtestPackage.SIMPLE_ALTERNATIVE:
				sequence_SimpleAlternative(context, (SimpleAlternative) semanticObject); 
				return; 
			case CsvalidationtestPackage.SIMPLE_GROUP:
				sequence_SimpleGroup(context, (SimpleGroup) semanticObject); 
				return; 
			case CsvalidationtestPackage.SIMPLE_MULTIPLICITIES:
				sequence_SimpleMultiplicities(context, (SimpleMultiplicities) semanticObject); 
				return; 
			case CsvalidationtestPackage.STATIC_SIMPLIFICATION:
				sequence_StaticSimplification(context, (StaticSimplification) semanticObject); 
				return; 
			case CsvalidationtestPackage.TRANSIENT_OBJECT:
				sequence_TransientObject(context, (TransientObject) semanticObject); 
				return; 
			case CsvalidationtestPackage.TRANSIENT_OBJECT_SUB:
				sequence_TransientObjectSub(context, (TransientObjectSub) semanticObject); 
				return; 
			case CsvalidationtestPackage.TRANSIENT_SERIALIZEABLES1:
				sequence_TransientSerializeables1(context, (TransientSerializeables1) semanticObject); 
				return; 
			case CsvalidationtestPackage.TWO_VERSION:
				if (rule == grammarAccess.getTwoVersionNo1Rule()) {
					sequence_TwoVersionNo1(context, (TwoVersion) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTwoVersionRule()) {
					sequence_TwoVersionNo1_TwoVersionNo2(context, (TwoVersion) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getTwoVersionNo2Rule()) {
					sequence_TwoVersionNo2(context, (TwoVersion) semanticObject); 
					return; 
				}
				else break;
			case CsvalidationtestPackage.UNASSIGNED_ACTION1:
				sequence_UnassignedAction1(context, (UnassignedAction1) semanticObject); 
				return; 
			case CsvalidationtestPackage.UNASSIGNED_ACTION2_SUB:
				sequence_UnassignedAction2(context, (UnassignedAction2Sub) semanticObject); 
				return; 
			case CsvalidationtestPackage.UNASSIGNED_ACTION2_SUB1:
				sequence_UnassignedAction3(context, (UnassignedAction2Sub1) semanticObject); 
				return; 
			case CsvalidationtestPackage.UNASSIGNED_ACTION2_SUB2:
				sequence_UnassignedAction3(context, (UnassignedAction2Sub2) semanticObject); 
				return; 
			case CsvalidationtestPackage.UNASSIGNED_RULE_CALL1_SUB:
				if (rule == grammarAccess.getUnassignedRuleCall1SubRule()) {
					sequence_UnassignedRuleCall1Sub(context, (UnassignedRuleCall1Sub) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnassignedRuleCall1Rule()) {
					sequence_UnassignedRuleCall1_UnassignedRuleCall1Sub(context, (UnassignedRuleCall1Sub) semanticObject); 
					return; 
				}
				else break;
			case CsvalidationtestPackage.UNASSIGNED_RULE_CALL2_SUB_ACTION:
				if (rule == grammarAccess.getUnassignedRuleCall2SubRule()) {
					sequence_UnassignedRuleCall2Sub(context, (UnassignedRuleCall2SubAction) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getUnassignedRuleCall2Rule()) {
					sequence_UnassignedRuleCall2_UnassignedRuleCall2Sub(context, (UnassignedRuleCall2SubAction) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     AltList1 returns AltList1
	 *
	 * Constraint:
	 *     ((val1=ID val2=ID) | (val1=ID val3=ID) | (val1=ID val4=ID?))
	 * </pre>
	 */
	protected void sequence_AltList1(ISerializationContext context, AltList1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AltList2 returns AltList2
	 *
	 * Constraint:
	 *     ((val1+=ID val2=ID) | (val1+=ID val1+=ID* val3=ID))
	 * </pre>
	 */
	protected void sequence_AltList2(ISerializationContext context, AltList2 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AlternativeMultiplicities returns AlternativeMultiplicities
	 *
	 * Constraint:
	 *     ((val2=ID | val3=ID)? (val4+=ID | val5+=ID)+ val6+=ID? (val7+=ID? val6+=ID?)*)
	 * </pre>
	 */
	protected void sequence_AlternativeMultiplicities(ISerializationContext context, AlternativeMultiplicities semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AssignedActionSecond returns AssignedAction
	 *
	 * Constraint:
	 *     (val1=ID val2=ID)
	 * </pre>
	 */
	protected void sequence_AssignedActionSecond(ISerializationContext context, AssignedAction semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__VAL1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__VAL1));
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__VAL2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__VAL2));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAssignedActionSecondAccess().getVal1IDTerminalRuleCall_1_0(), semanticObject.getVal1());
		feeder.accept(grammarAccess.getAssignedActionSecondAccess().getVal2IDTerminalRuleCall_3_0(), semanticObject.getVal2());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AssignedAction returns AssignedAction
	 *
	 * Constraint:
	 *     (child=AssignedAction_AssignedAction_2_0 val1=ID)
	 * </pre>
	 */
	protected void sequence_AssignedAction(ISerializationContext context, AssignedAction semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__CHILD) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__CHILD));
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__VAL1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.ASSIGNED_ACTION__VAL1));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getAssignedActionAccess().getAssignedActionChildAction_2_0(), semanticObject.getChild());
		feeder.accept(grammarAccess.getAssignedActionAccess().getVal1IDTerminalRuleCall_2_1_0(), semanticObject.getVal1());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     AssignedAction.AssignedAction_2_0 returns AssignedAction
	 *
	 * Constraint:
	 *     (val1=ID | (child=AssignedAction_AssignedAction_2_0 val1=ID))
	 * </pre>
	 */
	protected void sequence_AssignedAction_AssignedAction_2_0(ISerializationContext context, AssignedAction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Combination1 returns Combination1
	 *
	 * Constraint:
	 *     (val1=ID (val2=ID (val3=ID | val4=ID))?)
	 * </pre>
	 */
	protected void sequence_Combination1(ISerializationContext context, Combination1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Combination2 returns Combination2
	 *
	 * Constraint:
	 *     (val1=ID (val2=ID | (val3+=ID val4+=ID)+)?)
	 * </pre>
	 */
	protected void sequence_Combination2(ISerializationContext context, Combination2 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Combination3 returns Combination3
	 *
	 * Constraint:
	 *     (val1=ID | val2=INT | val3=STRING)+
	 * </pre>
	 */
	protected void sequence_Combination3(ISerializationContext context, Combination3 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Combination4 returns Combination4
	 *
	 * Constraint:
	 *     (val1+=ID val2+=ID val3+=ID)+
	 * </pre>
	 */
	protected void sequence_Combination4(ISerializationContext context, Combination4 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     GroupMultiplicities returns GroupMultiplicities
	 *
	 * Constraint:
	 *     (val1=ID (val2=ID val3=ID)? (val4+=ID val5+=ID)+ (val6+=ID val7+=ID)*)
	 * </pre>
	 */
	protected void sequence_GroupMultiplicities(ISerializationContext context, GroupMultiplicities semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Heuristic1 returns Heuristic1
	 *
	 * Constraint:
	 *     (((a+=ID b+=ID)* (a+=ID c+=ID)+ (b+=ID c+=ID)+) | ((a+=ID b+=ID)* (b+=ID c+=ID)+) | (b+=ID c+=ID)+)?
	 * </pre>
	 */
	protected void sequence_Heuristic1(ISerializationContext context, Heuristic1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     List1 returns List1
	 *
	 * Constraint:
	 *     (val1+=ID val1+=ID*)
	 * </pre>
	 */
	protected void sequence_List1(ISerializationContext context, List1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     List2 returns List2
	 *
	 * Constraint:
	 *     (val1+=ID val1+=ID*)
	 * </pre>
	 */
	protected void sequence_List2(ISerializationContext context, List2 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     List3 returns List3
	 *
	 * Constraint:
	 *     ((val1+=ID val1+=ID*) | val2=ID)
	 * </pre>
	 */
	protected void sequence_List3(ISerializationContext context, List3 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     List4 returns List4
	 *
	 * Constraint:
	 *     (val1+=ID val1+=ID* val2=ID)
	 * </pre>
	 */
	protected void sequence_List4(ISerializationContext context, List4 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     List5 returns List5
	 *
	 * Constraint:
	 *     ((val1+=ID val1+=ID* val2=ID) | val3=ID)
	 * </pre>
	 */
	protected void sequence_List5(ISerializationContext context, List5 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Model returns Model
	 *
	 * Constraint:
	 *     (
	 *         x1=SimpleGroup | 
	 *         x2=SimpleAlternative | 
	 *         x3=SimpleMultiplicities | 
	 *         x4=GroupMultiplicities | 
	 *         x5=AlternativeMultiplicities | 
	 *         x6=AssignedAction | 
	 *         x7=AssignedActionSecond | 
	 *         x8=UnassignedAction1 | 
	 *         x9=UnassignedAction2 | 
	 *         x10=UnassignedAction3 | 
	 *         x11=UnassignedRuleCall1 | 
	 *         x12=UnassignedRuleCall2 | 
	 *         x13=Combination1 | 
	 *         x14=Combination2 | 
	 *         x15=Combination3 | 
	 *         x16=Combination4 | 
	 *         x17=List1 | 
	 *         x18=List2 | 
	 *         x19=List3 | 
	 *         x20=List4 | 
	 *         x21=List5 | 
	 *         x22=AltList1 | 
	 *         x23=AltList2 | 
	 *         x24=TransientObject | 
	 *         x25=TransientSerializeables1 | 
	 *         x26=StaticSimplification | 
	 *         x27=TwoVersion | 
	 *         x28=Heuristic1
	 *     )
	 * </pre>
	 */
	protected void sequence_Model(ISerializationContext context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     SimpleAlternative returns SimpleAlternative
	 *
	 * Constraint:
	 *     (val1=ID | val2=ID)
	 * </pre>
	 */
	protected void sequence_SimpleAlternative(ISerializationContext context, SimpleAlternative semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     SimpleGroup returns SimpleGroup
	 *
	 * Constraint:
	 *     (val1=ID val2=ID)
	 * </pre>
	 */
	protected void sequence_SimpleGroup(ISerializationContext context, SimpleGroup semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.SIMPLE_GROUP__VAL1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.SIMPLE_GROUP__VAL1));
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.SIMPLE_GROUP__VAL2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.SIMPLE_GROUP__VAL2));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSimpleGroupAccess().getVal1IDTerminalRuleCall_1_0(), semanticObject.getVal1());
		feeder.accept(grammarAccess.getSimpleGroupAccess().getVal2IDTerminalRuleCall_2_0(), semanticObject.getVal2());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     SimpleMultiplicities returns SimpleMultiplicities
	 *
	 * Constraint:
	 *     (val1=ID val2=ID? val3+=ID+ val4+=ID*)
	 * </pre>
	 */
	protected void sequence_SimpleMultiplicities(ISerializationContext context, SimpleMultiplicities semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     StaticSimplification returns EmptyAlternativeSub
	 *
	 * Constraint:
	 *     (val2=ID? val3=ID*)
	 * </pre>
	 */
	protected void sequence_StaticSimplification(ISerializationContext context, EmptyAlternativeSub semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     StaticSimplification returns StaticSimplification
	 *
	 * Constraint:
	 *     (val1=ID? val2=ID? val3=ID*)
	 * </pre>
	 */
	protected void sequence_StaticSimplification(ISerializationContext context, StaticSimplification semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TransientObjectSub returns TransientObjectSub
	 *
	 * Constraint:
	 *     (val2=ID val3=ID)
	 * </pre>
	 */
	protected void sequence_TransientObjectSub(ISerializationContext context, TransientObjectSub semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT_SUB__VAL2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT_SUB__VAL2));
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT_SUB__VAL3) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT_SUB__VAL3));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getTransientObjectSubAccess().getVal2IDTerminalRuleCall_0_0(), semanticObject.getVal2());
		feeder.accept(grammarAccess.getTransientObjectSubAccess().getVal3IDTerminalRuleCall_1_0(), semanticObject.getVal3());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TransientObject returns TransientObject
	 *
	 * Constraint:
	 *     (val1=ID nested=TransientObjectSub)
	 * </pre>
	 */
	protected void sequence_TransientObject(ISerializationContext context, TransientObject semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT__VAL1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT__VAL1));
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT__NESTED) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.TRANSIENT_OBJECT__NESTED));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getTransientObjectAccess().getVal1IDTerminalRuleCall_1_0_0(), semanticObject.getVal1());
		feeder.accept(grammarAccess.getTransientObjectAccess().getNestedTransientObjectSubParserRuleCall_1_1_0(), semanticObject.getNested());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TransientSerializeables1 returns TransientSerializeables1
	 *
	 * Constraint:
	 *     ((val1=ID enum1=TransientSerializeables1Enum (val2=ID int1=INT)) | (val2=ID int1=INT))?
	 * </pre>
	 */
	protected void sequence_TransientSerializeables1(ISerializationContext context, TransientSerializeables1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TwoVersionNo1 returns TwoVersion
	 *
	 * Constraint:
	 *     (shared1=ID? shared2=ID (shared3+=ID shared3+=ID*)? version1=ID?)
	 * </pre>
	 */
	protected void sequence_TwoVersionNo1(ISerializationContext context, TwoVersion semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TwoVersion returns TwoVersion
	 *
	 * Constraint:
	 *     (
	 *         (shared1=ID? shared2=ID (shared3+=ID shared3+=ID*)? version1=ID?) | 
	 *         (shared1=ID? shared2=ID (shared3+=ID shared3+=ID*)? extra1=ID? ((extra2=ID extra3=ID) | extra4=ID)?)
	 *     )
	 * </pre>
	 */
	protected void sequence_TwoVersionNo1_TwoVersionNo2(ISerializationContext context, TwoVersion semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     TwoVersionNo2 returns TwoVersion
	 *
	 * Constraint:
	 *     (shared1=ID? shared2=ID (shared3+=ID shared3+=ID*)? extra1=ID? ((extra2=ID extra3=ID) | extra4=ID)?)
	 * </pre>
	 */
	protected void sequence_TwoVersionNo2(ISerializationContext context, TwoVersion semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedAction1 returns UnassignedAction1
	 *
	 * Constraint:
	 *     (val1=ID val2=ID?)
	 * </pre>
	 */
	protected void sequence_UnassignedAction1(ISerializationContext context, UnassignedAction1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedAction2 returns UnassignedAction2Sub
	 *
	 * Constraint:
	 *     (val1=ID val2=ID?)
	 * </pre>
	 */
	protected void sequence_UnassignedAction2(ISerializationContext context, UnassignedAction2Sub semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedAction3 returns UnassignedAction2Sub1
	 *
	 * Constraint:
	 *     (val1=ID val2=ID?)
	 * </pre>
	 */
	protected void sequence_UnassignedAction3(ISerializationContext context, UnassignedAction2Sub1 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedAction3 returns UnassignedAction2Sub2
	 *
	 * Constraint:
	 *     (val1=ID val2=ID?)
	 * </pre>
	 */
	protected void sequence_UnassignedAction3(ISerializationContext context, UnassignedAction2Sub2 semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedRuleCall1Sub returns UnassignedRuleCall1Sub
	 *
	 * Constraint:
	 *     val1=ID
	 * </pre>
	 */
	protected void sequence_UnassignedRuleCall1Sub(ISerializationContext context, UnassignedRuleCall1Sub semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL1_SUB__VAL1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL1_SUB__VAL1));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnassignedRuleCall1SubAccess().getVal1IDTerminalRuleCall_0(), semanticObject.getVal1());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedRuleCall1 returns UnassignedRuleCall1Sub
	 *
	 * Constraint:
	 *     (val1=ID val2=ID)
	 * </pre>
	 */
	protected void sequence_UnassignedRuleCall1_UnassignedRuleCall1Sub(ISerializationContext context, UnassignedRuleCall1Sub semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL1_SUB__VAL1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL1_SUB__VAL1));
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL1_SUB__VAL2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL1_SUB__VAL2));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnassignedRuleCall1SubAccess().getVal1IDTerminalRuleCall_0(), semanticObject.getVal1());
		feeder.accept(grammarAccess.getUnassignedRuleCall1Access().getVal2IDTerminalRuleCall_2_0(), semanticObject.getVal2());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedRuleCall2Sub returns UnassignedRuleCall2SubAction
	 *
	 * Constraint:
	 *     {UnassignedRuleCall2SubAction}
	 * </pre>
	 */
	protected void sequence_UnassignedRuleCall2Sub(ISerializationContext context, UnassignedRuleCall2SubAction semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     UnassignedRuleCall2 returns UnassignedRuleCall2SubAction
	 *
	 * Constraint:
	 *     val2=ID
	 * </pre>
	 */
	protected void sequence_UnassignedRuleCall2_UnassignedRuleCall2Sub(ISerializationContext context, UnassignedRuleCall2SubAction semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL2_SUB__VAL2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, CsvalidationtestPackage.Literals.UNASSIGNED_RULE_CALL2_SUB__VAL2));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getUnassignedRuleCall2Access().getVal2IDTerminalRuleCall_2_0(), semanticObject.getVal2());
		feeder.finish();
	}
	
	
}
