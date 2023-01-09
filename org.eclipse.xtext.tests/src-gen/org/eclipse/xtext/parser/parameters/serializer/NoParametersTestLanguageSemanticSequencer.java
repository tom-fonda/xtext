/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parser.parameters.serializer;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.parser.parameters.parametersTestLanguage.ParametersTestLanguagePackage;
import org.eclipse.xtext.parser.parameters.parametersTestLanguage.ParserRuleParameters;
import org.eclipse.xtext.parser.parameters.parametersTestLanguage.Scenario;
import org.eclipse.xtext.parser.parameters.services.NoParametersTestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class NoParametersTestLanguageSemanticSequencer extends ParametersTestLanguageSemanticSequencer {

	@Inject
	private NoParametersTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == ParametersTestLanguagePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case ParametersTestLanguagePackage.PARSER_RULE_PARAMETERS:
				sequence_ParserRuleParameters(context, (ParserRuleParameters) semanticObject); 
				return; 
			case ParametersTestLanguagePackage.SCENARIO:
				if (rule == grammarAccess.getScenario1Rule() && (parameters.isEmpty())) {
					sequence_Scenario1$Param$false$(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario1Rule() && (ImmutableSet.of(grammarAccess.getScenario1Rule().getParameters().get(0/*Param*/)).equals(parameters))) {
					sequence_Scenario1$Param$true$(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario1_FalseRule()) {
					sequence_Scenario1_False(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario1_TrueRule()) {
					sequence_Scenario1_True(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario2_FalseRule()) {
					sequence_Scenario2_False(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario2Rule()) {
					sequence_Scenario2(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario2_TrueRule()) {
					sequence_Scenario2_True(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario3_FalseRule()) {
					sequence_Scenario3_False(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario3Rule()) {
					sequence_Scenario3(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario3_TrueRule()) {
					sequence_Scenario3_True(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario4_FalseRule()) {
					sequence_Scenario4_False(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario4Rule()) {
					sequence_Scenario4(context, (Scenario) semanticObject); 
					return; 
				}
				else if (rule == grammarAccess.getScenario4_TrueRule()) {
					sequence_Scenario4_True(context, (Scenario) semanticObject); 
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
	 *     ParserRuleParameters returns ParserRuleParameters
	 *
	 * Constraint:
	 *     (
	 *         scenario=Scenario1_True | 
	 *         scenario=Scenario1_False | 
	 *         scenario=Scenario2_True | 
	 *         scenario=Scenario2_False | 
	 *         scenario=Scenario2_True | 
	 *         scenario=Scenario2_False | 
	 *         scenario=Scenario3_True | 
	 *         scenario=Scenario3_False | 
	 *         scenario=Scenario4_True | 
	 *         scenario=Scenario2_True | 
	 *         scenario=Scenario4_True | 
	 *         scenario=Scenario2_False | 
	 *         scenario=Scenario4_False | 
	 *         scenario=Scenario2_True | 
	 *         scenario=Scenario4_False | 
	 *         scenario=Scenario2_False
	 *     )
	 * </pre>
	 */
	protected void sequence_ParserRuleParameters(ISerializationContext context, ParserRuleParameters semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario1_False returns Scenario
	 *
	 * Constraint:
	 *     second=ID
	 * </pre>
	 */
	protected void sequence_Scenario1_False(ISerializationContext context, Scenario semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__SECOND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__SECOND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getScenario1_FalseAccess().getSecondIDTerminalRuleCall_0(), semanticObject.getSecond());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario1_True returns Scenario
	 *
	 * Constraint:
	 *     first=ID
	 * </pre>
	 */
	protected void sequence_Scenario1_True(ISerializationContext context, Scenario semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__FIRST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__FIRST));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getScenario1_TrueAccess().getFirstIDTerminalRuleCall_0(), semanticObject.getFirst());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario2_False returns Scenario
	 *
	 * Constraint:
	 *     first=IdOrKeyword_False
	 * </pre>
	 */
	protected void sequence_Scenario2_False(ISerializationContext context, Scenario semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__FIRST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__FIRST));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getScenario2_FalseAccess().getFirstIdOrKeyword_FalseParserRuleCall_0(), semanticObject.getFirst());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario2_True returns Scenario
	 *
	 * Constraint:
	 *     first=IdOrKeyword_True
	 * </pre>
	 */
	protected void sequence_Scenario2_True(ISerializationContext context, Scenario semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__FIRST) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__FIRST));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getScenario2_TrueAccess().getFirstIdOrKeyword_TrueParserRuleCall_0(), semanticObject.getFirst());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario3_False returns Scenario
	 *
	 * Constraint:
	 *     (first=IdOrKeyword_False | second='keyword')
	 * </pre>
	 */
	protected void sequence_Scenario3_False(ISerializationContext context, Scenario semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario3_True returns Scenario
	 *
	 * Constraint:
	 *     (first=IdOrKeyword_True | second='keyword')
	 * </pre>
	 */
	protected void sequence_Scenario3_True(ISerializationContext context, Scenario semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario4_False returns Scenario
	 *
	 * Constraint:
	 *     second=IdOrKeyword_False
	 * </pre>
	 */
	protected void sequence_Scenario4_False(ISerializationContext context, Scenario semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__SECOND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__SECOND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getScenario4_FalseAccess().getSecondIdOrKeyword_FalseParserRuleCall_0_0(), semanticObject.getSecond());
		feeder.finish();
	}
	
	
	/**
	 * <pre>
	 * Contexts:
	 *     Scenario4_True returns Scenario
	 *
	 * Constraint:
	 *     second=IdOrKeyword_True
	 * </pre>
	 */
	protected void sequence_Scenario4_True(ISerializationContext context, Scenario semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__SECOND) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ParametersTestLanguagePackage.Literals.SCENARIO__SECOND));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getScenario4_TrueAccess().getSecondIdOrKeyword_TrueParserRuleCall_0_0(), semanticObject.getSecond());
		feeder.finish();
	}
	
	
}
