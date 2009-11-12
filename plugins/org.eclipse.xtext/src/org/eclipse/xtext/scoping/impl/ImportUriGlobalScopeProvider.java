/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.scoping.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.IQualifiedNameProvider;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopedElement;
import org.eclipse.xtext.scoping.Scopes;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class ImportUriGlobalScopeProvider extends AbstractScopeProvider implements IGlobalScopeProvider {
	
	private ImportUriResolver importResolver = null;
	private IQualifiedNameProvider nameProvider = null;
	
	@Inject
	public void setImportResolver(ImportUriResolver importResolver) {
		this.importResolver = importResolver;
	}
	
	@Inject
	public void setNameProvider(IQualifiedNameProvider nameProvider) {
		this.nameProvider = nameProvider;
	}
	
	public IScope getScope(EObject context, EReference reference) {
		TreeIterator<EObject> iterator = context.eResource().getAllContents();
		final LinkedHashSet<URI> uniqueImportURIs = new LinkedHashSet<URI>(10);
		while (iterator.hasNext()) {
			EObject object = iterator.next();
			String uri = importResolver.apply(object);
			if (uri != null) {
				URI importUri = URI.createURI(uri);
				if (EcoreUtil2.isValidUri(object, importUri))
					uniqueImportURIs.add(importUri); 
			}
		}
	
		ArrayList<URI> newArrayList = Lists.newArrayList(uniqueImportURIs);
		Collections.reverse(newArrayList);
		IScope scope = IScope.NULLSCOPE;
		for (URI u : newArrayList) {
			scope = createLazyResourceScope(scope,u,context,reference,nameProvider);
		}
		return scope;
	}

	private SimpleScope createLazyResourceScope(IScope parent, final URI createURI, final EObject context, final EReference reference,
			final Function<EObject, String> nameFunction) {
		return new SimpleScope(parent, null) {
			@Override
			public Iterable<IScopedElement> getContents() {
				final Resource resource = context.eResource().getResourceSet().getResource(createURI, true);
				return Scopes.allInResource(resource, reference.getEReferenceType(), nameFunction);
			}
		};
	}
	
	
}
