/*******************************************************************************
 * Copyright (c) 2010, 2023 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *******************************************************************************/
package org.eclipse.xtext.parser.parameters.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.parameters.parser.antlr.internal.InternalParametersTestLanguageExParser;
import org.eclipse.xtext.parser.parameters.services.ParametersTestLanguageExGrammarAccess;

public class ParametersTestLanguageExParser extends AbstractAntlrParser {

	@Inject
	private ParametersTestLanguageExGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalParametersTestLanguageExParser createParser(XtextTokenStream stream) {
		return new InternalParametersTestLanguageExParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "ParserRuleParameters";
	}

	public ParametersTestLanguageExGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ParametersTestLanguageExGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
