/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parsetree.formatter.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.Assign;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.Datatypes;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.Decl;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.Enumeration;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.FormattertestlanguagePackage;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.FqnObj;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.FqnRef;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.Meth;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.Param;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.Space;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.SuppressedHidden;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.SuppressedHiddenSubID;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.SuppressedHiddenSubSub;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.TestIndentation;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.TestLinewrap;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.TestLinewrapMinMax;
import org.eclipse.xtext.parsetree.formatter.formattertestlanguage.WrappingDataTypeTest;
import org.eclipse.xtext.parsetree.formatter.services.FormatterTestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class FormatterTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private FormatterTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == FormattertestlanguagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case FormattertestlanguagePackage.ASSIGN:
				sequence_Assign(context, (Assign) semanticObject); 
				return; 
			case FormattertestlanguagePackage.DATATYPES:
				sequence_Datatypes(context, (Datatypes) semanticObject); 
				return; 
			case FormattertestlanguagePackage.DECL:
				sequence_Decl(context, (Decl) semanticObject); 
				return; 
			case FormattertestlanguagePackage.ENUMERATION:
				sequence_Enumeration(context, (Enumeration) semanticObject); 
				return; 
			case FormattertestlanguagePackage.FQN_OBJ:
				sequence_FqnObj(context, (FqnObj) semanticObject); 
				return; 
			case FormattertestlanguagePackage.FQN_REF:
				sequence_FqnRef(context, (FqnRef) semanticObject); 
				return; 
			case FormattertestlanguagePackage.METH:
				sequence_Meth(context, (Meth) semanticObject); 
				return; 
			case FormattertestlanguagePackage.PARAM:
				sequence_Param(context, (Param) semanticObject); 
				return; 
			case FormattertestlanguagePackage.SPACE:
				sequence_Space(context, (Space) semanticObject); 
				return; 
			case FormattertestlanguagePackage.SUPPRESSED_HIDDEN:
				sequence_SuppressedHidden(context, (SuppressedHidden) semanticObject); 
				return; 
			case FormattertestlanguagePackage.SUPPRESSED_HIDDEN_SUB_ID:
				sequence_SuppressedHiddenSubID(context, (SuppressedHiddenSubID) semanticObject); 
				return; 
			case FormattertestlanguagePackage.SUPPRESSED_HIDDEN_SUB_SUB:
				sequence_SuppressedHiddenSubSub(context, (SuppressedHiddenSubSub) semanticObject); 
				return; 
			case FormattertestlanguagePackage.TEST_INDENTATION:
				sequence_TestIndentation(context, (TestIndentation) semanticObject); 
				return; 
			case FormattertestlanguagePackage.TEST_LINEWRAP:
				sequence_TestLinewrap(context, (TestLinewrap) semanticObject); 
				return; 
			case FormattertestlanguagePackage.TEST_LINEWRAP_MIN_MAX:
				sequence_TestLinewrapMinMax(context, (TestLinewrapMinMax) semanticObject); 
				return; 
			case FormattertestlanguagePackage.WRAPPING_DATA_TYPE_TEST:
				sequence_WrappingDataTypeTest(context, (WrappingDataTypeTest) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns Assign
	 *     Assign returns Assign
	 *
	 * Constraint:
	 *     (var=ID (op='=' | op='+=') (val+=INT val+=INT*)?)
	 * </pre>
	 */
	protected void sequence_Assign(ISerializationContext context, Assign semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns Datatypes
	 *     Datatypes returns Datatypes
	 *
	 * Constraint:
	 *     (val1=Datatype1 val2=Datatype2 val3=Datatype3)
	 * </pre>
	 */
	protected void sequence_Datatypes(ISerializationContext context, Datatypes semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.DATATYPES__VAL1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.DATATYPES__VAL1));
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.DATATYPES__VAL2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.DATATYPES__VAL2));
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.DATATYPES__VAL3) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.DATATYPES__VAL3));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getDatatypesAccess().getVal1Datatype1ParserRuleCall_1_0(), semanticObject.getVal1());
		feeder.accept(grammarAccess.getDatatypesAccess().getVal2Datatype2ParserRuleCall_3_0(), semanticObject.getVal2());
		feeder.accept(grammarAccess.getDatatypesAccess().getVal3Datatype3ParserRuleCall_4_0(), semanticObject.getVal3());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns Decl
	 *     Decl returns Decl
	 *
	 * Constraint:
	 *     (type+=ID name+=ID)
	 * </pre>
	 */
	protected void sequence_Decl(ISerializationContext context, Decl semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns Enumeration
	 *     Enumeration returns Enumeration
	 *
	 * Constraint:
	 *     (val+=Enum1+ val+=Enum1*)
	 * </pre>
	 */
	protected void sequence_Enumeration(ISerializationContext context, Enumeration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns FqnObj
	 *     FqnObj returns FqnObj
	 *
	 * Constraint:
	 *     name=FQN
	 * </pre>
	 */
	protected void sequence_FqnObj(ISerializationContext context, FqnObj semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.FQN_OBJ__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.FQN_OBJ__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFqnObjAccess().getNameFQNParserRuleCall_1_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns FqnRef
	 *     FqnRef returns FqnRef
	 *
	 * Constraint:
	 *     ref=[FqnObj|FQN]
	 * </pre>
	 */
	protected void sequence_FqnRef(ISerializationContext context, FqnRef semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.FQN_REF__REF) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.FQN_REF__REF));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getFqnRefAccess().getRefFqnObjFQNParserRuleCall_1_0_1(), semanticObject.eGet(FormattertestlanguagePackage.Literals.FQN_REF__REF, false));
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns Meth
	 *     Meth returns Meth
	 *
	 * Constraint:
	 *     (name=ID (param+=Param param+=Param*)?)
	 * </pre>
	 */
	protected void sequence_Meth(ISerializationContext context, Meth semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Param returns Param
	 *
	 * Constraint:
	 *     (name+=ID type+=ID)
	 * </pre>
	 */
	protected void sequence_Param(ISerializationContext context, Param semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns Space
	 *     Space returns Space
	 *
	 * Constraint:
	 *     val=ID
	 * </pre>
	 */
	protected void sequence_Space(ISerializationContext context, Space semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.SPACE__VAL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.SPACE__VAL));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSpaceAccess().getValIDTerminalRuleCall_1_0(), semanticObject.getVal());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     SuppressedHiddenSub returns SuppressedHiddenSubID
	 *     SuppressedHiddenSubID returns SuppressedHiddenSubID
	 *
	 * Constraint:
	 *     idval=ID
	 * </pre>
	 */
	protected void sequence_SuppressedHiddenSubID(ISerializationContext context, SuppressedHiddenSubID semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.SUPPRESSED_HIDDEN_SUB__IDVAL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.SUPPRESSED_HIDDEN_SUB__IDVAL));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSuppressedHiddenSubIDAccess().getIdvalIDTerminalRuleCall_0(), semanticObject.getIdval());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     SuppressedHiddenSub returns SuppressedHiddenSubSub
	 *     SuppressedHiddenSubSub returns SuppressedHiddenSubSub
	 *
	 * Constraint:
	 *     idval=ID
	 * </pre>
	 */
	protected void sequence_SuppressedHiddenSubSub(ISerializationContext context, SuppressedHiddenSubSub semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.SUPPRESSED_HIDDEN_SUB__IDVAL) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.SUPPRESSED_HIDDEN_SUB__IDVAL));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSuppressedHiddenSubSubAccess().getIdvalIDTerminalRuleCall_1_0(), semanticObject.getIdval());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Line returns SuppressedHidden
	 *     SuppressedHidden returns SuppressedHidden
	 *
	 * Constraint:
	 *     (vals+=SuppressedHiddenSub vals+=SuppressedHiddenSub*)?
	 * </pre>
	 */
	protected void sequence_SuppressedHidden(ISerializationContext context, SuppressedHidden semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Root returns TestIndentation
	 *     TestIndentation returns TestIndentation
	 *
	 * Constraint:
	 *     ((items+=Line | items+=TestIndentation)* semi?=';'?)
	 * </pre>
	 */
	protected void sequence_TestIndentation(ISerializationContext context, TestIndentation semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Root returns TestLinewrapMinMax
	 *     TestLinewrapMinMax returns TestLinewrapMinMax
	 *
	 * Constraint:
	 *     items+=Line*
	 * </pre>
	 */
	protected void sequence_TestLinewrapMinMax(ISerializationContext context, TestLinewrapMinMax semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Root returns TestLinewrap
	 *     TestLinewrap returns TestLinewrap
	 *
	 * Constraint:
	 *     items+=Line*
	 * </pre>
	 */
	protected void sequence_TestLinewrap(ISerializationContext context, TestLinewrap semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Root returns WrappingDataTypeTest
	 *     WrappingDataTypeTest returns WrappingDataTypeTest
	 *
	 * Constraint:
	 *     datatype=WrappingDataType
	 * </pre>
	 */
	protected void sequence_WrappingDataTypeTest(ISerializationContext context, WrappingDataTypeTest semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, FormattertestlanguagePackage.Literals.WRAPPING_DATA_TYPE_TEST__DATATYPE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, FormattertestlanguagePackage.Literals.WRAPPING_DATA_TYPE_TEST__DATATYPE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getWrappingDataTypeTestAccess().getDatatypeWrappingDataTypeParserRuleCall_1_0(), semanticObject.getDatatype());
		feeder.finish();
	}
	
	
}
