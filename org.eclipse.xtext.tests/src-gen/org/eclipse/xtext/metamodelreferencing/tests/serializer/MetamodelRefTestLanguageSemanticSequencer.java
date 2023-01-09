/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.metamodelreferencing.tests.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.metamodelreferencing.tests.anotherSimpleTest.AnotherSimpleTestPackage;
import org.eclipse.xtext.metamodelreferencing.tests.anotherSimpleTest.Foo;
import org.eclipse.xtext.metamodelreferencing.tests.services.MetamodelRefTestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class MetamodelRefTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private MetamodelRefTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == AnotherSimpleTestPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case AnotherSimpleTestPackage.FOO:
				sequence_Foo(context, (Foo) semanticObject); 
				return; 
			}
		else if (epackage == XtextPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case XtextPackage.PARSER_RULE:
				sequence_MyRule(context, (ParserRule) semanticObject); 
				return; 
			case XtextPackage.RULE_CALL:
				sequence_NameRef(context, (RuleCall) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Foo returns Foo
	 *
	 * Constraint:
	 *     (name=ID nameRefs+=NameRef*)
	 * </pre>
	 */
	protected void sequence_Foo(ISerializationContext context, Foo semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     MyRule returns ParserRule
	 *
	 * Constraint:
	 *     name=ID
	 * </pre>
	 */
	protected void sequence_MyRule(ISerializationContext context, ParserRule semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, XtextPackage.Literals.ABSTRACT_RULE__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextPackage.Literals.ABSTRACT_RULE__NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getMyRuleAccess().getNameIDTerminalRuleCall_0(), semanticObject.getName());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     NameRef returns RuleCall
	 *
	 * Constraint:
	 *     rule=[ParserRule|ID]
	 * </pre>
	 */
	protected void sequence_NameRef(ISerializationContext context, RuleCall semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, XtextPackage.Literals.RULE_CALL__RULE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, XtextPackage.Literals.RULE_CALL__RULE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getNameRefAccess().getRuleParserRuleIDTerminalRuleCall_0_1(), semanticObject.eGet(XtextPackage.Literals.RULE_CALL__RULE, false));
		feeder.finish();
	}
	
	
}
