/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.valueconverter.serializer;

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
import org.eclipse.xtext.valueconverter.qualifiedName.Element;
import org.eclipse.xtext.valueconverter.qualifiedName.QualifiedNamePackage;
import org.eclipse.xtext.valueconverter.services.QualifiedNameTestLanguageGrammarAccess;

@SuppressWarnings("all")
public class QualifiedNameTestLanguageSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private QualifiedNameTestLanguageGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == QualifiedNamePackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case QualifiedNamePackage.ELEMENT:
				sequence_Element(context, (Element) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * <pre>
	 * Contexts:
	 *     Element returns Element
	 *
	 * Constraint:
	 *     qualifiedName=QualifiedName
	 * </pre>
	 */
	protected void sequence_Element(ISerializationContext context, Element semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, QualifiedNamePackage.Literals.ELEMENT__QUALIFIED_NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, QualifiedNamePackage.Literals.ELEMENT__QUALIFIED_NAME));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getElementAccess().getQualifiedNameQualifiedNameParserRuleCall_1_0(), semanticObject.getQualifiedName());
		feeder.finish();
	}
	
	
}
