/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.generator.types;

import java.util.Set;

import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.common.types.access.ClasspathTypeProviderFactory;
import org.eclipse.xtext.common.types.access.ITypeProvider;
import org.eclipse.xtext.common.types.xtext.AbstractTypeScopeProvider;
import org.eclipse.xtext.common.types.xtext.ClasspathBasedTypeScopeProvider;
import org.eclipse.xtext.generator.BindFactory;
import org.eclipse.xtext.generator.Binding;
import org.eclipse.xtext.generator.DefaultGeneratorFragment;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class TypesGeneratorFragment extends DefaultGeneratorFragment {

	static {
		TypesPackage.eINSTANCE.getArrayType();
	}
	
	@Override
	public Set<Binding> getGuiceBindingsRt(Grammar grammar) {
		return new BindFactory()
		 .addTypeToType(ITypeProvider.Factory.class.getName(), ClasspathTypeProviderFactory.class.getName())
		 .addTypeToType(AbstractTypeScopeProvider.class.getName(), ClasspathBasedTypeScopeProvider.class.getName())
		 .getBindings();
	}
	
	@Override
	public Set<Binding> getGuiceBindingsUi(Grammar grammar) {
		return new BindFactory()
		 .addTypeToType(ITypeProvider.Factory.class.getName(), "org.eclipse.xtext.common.types.access.jdt.JdtTypeProviderFactory")
		 .addTypeToType(AbstractTypeScopeProvider.class.getName(), "org.eclipse.xtext.common.types.xtext.ui.JdtBasedSimpleTypeScopeProvider")
		 .addTypeToType("org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider", "org.eclipse.xtext.common.types.xtext.ui.JdtTypesProposalProvider")
		 .addTypeToType("org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider", 
				 	"org.eclipse.xtext.common.types.xtext.ui.XtextResourceSetBasedProjectProvider")
		 .addTypeToType("org.eclipse.xtext.ui.common.editor.hyperlinking.IHyperlinkHelper", 
				 	"org.eclipse.xtext.common.types.xtext.ui.TypeAwareHyperlinkHelper")
		 .addTypeToType("org.eclipse.xtext.ui.core.editor.contentassist.PrefixMatcher", 
				 	"org.eclipse.xtext.ui.core.editor.contentassist.FQNPrefixMatcher")
		 .getBindings();
	}
	
	@Override
	public String[] getRequiredBundlesRt(Grammar grammar) {
		return new String[] { "org.eclipse.xtext.common.types" };
	}
	
	@Override
	public String[] getRequiredBundlesUi(Grammar grammar) {
		return new String[] { "org.eclipse.xtext.common.types.ui" };
	}
	
}
