/*******************************************************************************
 * Copyright (c) 2008 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.scoping.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.linking.impl.SimpleAttributeResolver;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.IScopedElement;
import org.eclipse.xtext.scoping.Scopes;

import com.google.common.base.Function;

/**
 * @author Sven Efftinge - Initial contribution and API
 */
public class DefaultScope extends SimpleScope {

	public DefaultScope(Resource resource, EClass type, ImportUriResolver resolver) {
		this(resource, type, resolver, SimpleAttributeResolver.NAME_RESOLVER);
	}
	
	public DefaultScope(Resource resource, EClass type, Function<EObject, String> importResolver, Function<EObject, String> nameResolver) {
		super(createParent(resource.getAllContents(), type, resource, importResolver, nameResolver), Scopes.allInResource(resource,type,nameResolver));
	}


	private static IScope createParent(Iterator<EObject> iter, EClass type, Resource resource, Function<EObject, String> importResolver, Function<EObject, String> nameResolver) {
		final Set<String> uniqueImportURIs = new HashSet<String>(10);
		final List<String> orderedImportURIs = new ArrayList<String>(10);
		while (iter.hasNext()) {
			EObject object = iter.next();
			String uri = importResolver.apply(object);
			if (uri != null && uniqueImportURIs.add(uri) && EcoreUtil2.isValidUri(object, URI.createURI(uri))) {
				orderedImportURIs.add(uri);
			}
		}
		IScope result = IScope.NULLSCOPE;
		for(int i = orderedImportURIs.size() - 1; i >= 0; i--) {
			result = new LazyReferencedResourceScope(result, type, resource, orderedImportURIs.get(i), nameResolver);
		}
		return result;
	}
	
	static class LazyReferencedResourceScope extends SimpleScope {

		private EClass type;
		private Resource context;
		private String uri;
		private Function<EObject, String> nameFunction;

		public LazyReferencedResourceScope(IScope parent, EClass type, Resource context, String uri, Function<EObject, String> nameFunc) {
			super(parent, null);
			this.type = type;
			this.context = context;
			this.uri = uri;
			this.nameFunction = nameFunc;
		}
		
		@Override
		public Iterable<IScopedElement> getContents() {
			Resource resource = EcoreUtil2.getResource(context, uri);
			return Scopes.allInResource(resource, type, nameFunction);
		}

	}
}
