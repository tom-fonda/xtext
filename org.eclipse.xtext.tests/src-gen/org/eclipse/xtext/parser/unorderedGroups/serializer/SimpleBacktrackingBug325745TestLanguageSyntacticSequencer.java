/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parser.unorderedGroups.serializer;

import com.google.inject.Inject;
import java.util.List;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.parser.unorderedGroups.services.SimpleBacktrackingBug325745TestLanguageGrammarAccess;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.TokenAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynNavigable;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class SimpleBacktrackingBug325745TestLanguageSyntacticSequencer extends AbstractSyntacticSequencer {

	protected SimpleBacktrackingBug325745TestLanguageGrammarAccess grammarAccess;
	protected AbstractElementAlias match_Expression_LeftSquareBracketKeyword_1_0_a;
	protected AbstractElementAlias match_Expression_LeftSquareBracketKeyword_2_0_q;
	protected AbstractElementAlias match_Expression_RightSquareBracketKeyword_2_2_q;
	protected AbstractElementAlias match_Expression_RightSquareBracketKeyword_3_0_a;
	protected AbstractElementAlias match_SimpleTerm_AsteriskKeyword_0_1_q;
	protected AbstractElementAlias match_SimpleTerm_ExclamationMarkKeyword_0_3_q;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (SimpleBacktrackingBug325745TestLanguageGrammarAccess) access;
		match_Expression_LeftSquareBracketKeyword_1_0_a = new TokenAlias(true, true, grammarAccess.getExpressionAccess().getLeftSquareBracketKeyword_1_0());
		match_Expression_LeftSquareBracketKeyword_2_0_q = new TokenAlias(false, true, grammarAccess.getExpressionAccess().getLeftSquareBracketKeyword_2_0());
		match_Expression_RightSquareBracketKeyword_2_2_q = new TokenAlias(false, true, grammarAccess.getExpressionAccess().getRightSquareBracketKeyword_2_2());
		match_Expression_RightSquareBracketKeyword_3_0_a = new TokenAlias(true, true, grammarAccess.getExpressionAccess().getRightSquareBracketKeyword_3_0());
		match_SimpleTerm_AsteriskKeyword_0_1_q = new TokenAlias(false, true, grammarAccess.getSimpleTermAccess().getAsteriskKeyword_0_1());
		match_SimpleTerm_ExclamationMarkKeyword_0_3_q = new TokenAlias(false, true, grammarAccess.getSimpleTermAccess().getExclamationMarkKeyword_0_3());
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		return "";
	}
	
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			if (match_Expression_LeftSquareBracketKeyword_1_0_a.equals(syntax))
				emit_Expression_LeftSquareBracketKeyword_1_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Expression_LeftSquareBracketKeyword_2_0_q.equals(syntax))
				emit_Expression_LeftSquareBracketKeyword_2_0_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Expression_RightSquareBracketKeyword_2_2_q.equals(syntax))
				emit_Expression_RightSquareBracketKeyword_2_2_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_Expression_RightSquareBracketKeyword_3_0_a.equals(syntax))
				emit_Expression_RightSquareBracketKeyword_3_0_a(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_SimpleTerm_AsteriskKeyword_0_1_q.equals(syntax))
				emit_SimpleTerm_AsteriskKeyword_0_1_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else if (match_SimpleTerm_ExclamationMarkKeyword_0_3_q.equals(syntax))
				emit_SimpleTerm_ExclamationMarkKeyword_0_3_q(semanticObject, getLastNavigableState(), syntaxNodes);
			else acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     '['*
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) (ambiguity) '['? terms+=SimpleTerm
	 *     (rule start) (ambiguity) ']'* (rule start)
	 *     (rule start) (ambiguity) ']'* postfix=STRING
	 *     (rule start) (ambiguity) prefix=STRING
	 *     prefix=STRING (ambiguity) '['? terms+=SimpleTerm
	 *     prefix=STRING (ambiguity) ']'* (rule end)
	 *     prefix=STRING (ambiguity) ']'* postfix=STRING
	 *     prefix=STRING (ambiguity) prefix=STRING
	 
	 * </pre>
	 */
	protected void emit_Expression_LeftSquareBracketKeyword_1_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     '['?
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) '['* (ambiguity) terms+=SimpleTerm
	 *     prefix=STRING '['* (ambiguity) terms+=SimpleTerm
	 *     terms+=SimpleTerm ']'? (ambiguity) terms+=SimpleTerm
	 
	 * </pre>
	 */
	protected void emit_Expression_LeftSquareBracketKeyword_2_0_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     ']'?
	 *
	 * This ambiguous syntax occurs at:
	 *     terms+=SimpleTerm (ambiguity) '['? terms+=SimpleTerm
	 *     terms+=SimpleTerm (ambiguity) ']'* (rule end)
	 *     terms+=SimpleTerm (ambiguity) ']'* postfix=STRING
	 
	 * </pre>
	 */
	protected void emit_Expression_RightSquareBracketKeyword_2_2_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     ']'*
	 *
	 * This ambiguous syntax occurs at:
	 *     (rule start) '['* (ambiguity) (rule start)
	 *     (rule start) '['* (ambiguity) postfix=STRING
	 *     postfix=STRING (ambiguity) (rule end)
	 *     postfix=STRING (ambiguity) postfix=STRING
	 *     prefix=STRING '['* (ambiguity) (rule end)
	 *     prefix=STRING '['* (ambiguity) postfix=STRING
	 *     terms+=SimpleTerm ']'? (ambiguity) (rule end)
	 *     terms+=SimpleTerm ']'? (ambiguity) postfix=STRING
	 
	 * </pre>
	 */
	protected void emit_Expression_RightSquareBracketKeyword_3_0_a(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     '*'?
	 *
	 * This ambiguous syntax occurs at:
	 *     lineCount=INT (ambiguity) '!'? (rule end)
	 *     lineCount=INT (ambiguity) '!'? charSet=ID
	 *     lineCount=INT (ambiguity) charCount=INT
	 
	 * </pre>
	 */
	protected void emit_SimpleTerm_AsteriskKeyword_0_1_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
	/**
	 * <pre>
	 * Ambiguous syntax:
	 *     '!'?
	 *
	 * This ambiguous syntax occurs at:
	 *     charCount=INT (ambiguity) (rule end)
	 *     charCount=INT (ambiguity) charSet=ID
	 *     lineCount=INT '*'? (ambiguity) (rule end)
	 *     lineCount=INT '*'? (ambiguity) charSet=ID
	 
	 * </pre>
	 */
	protected void emit_SimpleTerm_ExclamationMarkKeyword_0_3_q(EObject semanticObject, ISynNavigable transition, List<INode> nodes) {
		acceptNodes(transition, nodes);
	}
	
}
