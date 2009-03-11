/*******************************************************************************
 * Copyright (c) 2009 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.crossrefs;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.xtext.junit.AbstractXtextTests;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.StringInputStream;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
public class Bug266082Test extends AbstractXtextTests {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		with(ImportUriTestLanguageStandaloneSetup.class);
	}

	public void testCircularImports() {
		XtextResourceSet resourceSet = get(XtextResourceSet.class);
		resourceSet.setClasspathURIContext(getClass().getClassLoader());
		URI uri = URI.createURI("classpath:/org/eclipse/xtext/crossrefs/01.importuritestlanguage");
		Resource res = resourceSet.getResource(uri, true);
		assertNotNull("res", res);
		assertFalse(res.getErrors().toString(), res.getErrors().isEmpty());
	}

	public void testManyUnresolvedImports() throws Exception {
		StringBuilder model = new StringBuilder(200000);
		int max = 10000;
		for(int i = 1; i <= max; i++) {
			model.append("import '" + i + ".importuritestlanguage'\n");
		}
		model.append("type Foo extends Bar");
		Resource res = getResourceFromString(model.toString());
		assertEquals(res.getErrors().toString(), 1, res.getErrors().size());
	}

	public void testManyResolvedImports() throws Exception {
		StringBuilder model = new StringBuilder(2000);
		int max = 100;
		for(int i = 1; i <= max; i++) {
			model.append("import '" + i + ".importuritestlanguage'\n");
		}
		model.append("type Foo extends Bar");

		final String modelAsString = model.toString();
		model = null;

		XtextResourceSet resourceSet = get(XtextResourceSet.class);
		resourceSet.setClasspathURIContext(getClass().getClassLoader());
		final URIConverter converter = resourceSet.getURIConverter();
		resourceSet.setURIConverter(new URIConverter() {

			public Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException {
				return converter.contentDescription(uri, options);
			}

			public InputStream createInputStream(URI uri) throws IOException {
				return new StringInputStream(modelAsString);
			}

			public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException {
				return new StringInputStream(modelAsString);
			}

			public OutputStream createOutputStream(URI uri) throws IOException {
				return converter.createOutputStream(uri);
			}

			public OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException {
				return converter.createOutputStream(uri, options);
			}

			public void delete(URI uri, Map<?, ?> options) throws IOException {
				converter.delete(uri, options);
			}

			public boolean exists(URI uri, Map<?, ?> options) {
				return true;
			}

			public Map<String, ?> getAttributes(URI uri, Map<?, ?> options) {
				return converter.getAttributes(uri, options);
			}

			public EList<ContentHandler> getContentHandlers() {
				return converter.getContentHandlers();
			}

			public URIHandler getURIHandler(URI uri) {
				return converter.getURIHandler(uri);
			}

			public EList<URIHandler> getURIHandlers() {
				return converter.getURIHandlers();
			}

			public Map<URI, URI> getURIMap() {
				return converter.getURIMap();
			}

			public URI normalize(URI uri) {
				return converter.normalize(uri);
			}

			public void setAttributes(URI uri, Map<String, ?> attributes, Map<?, ?> options) throws IOException {
				converter.setAttributes(uri, attributes, options);
			}

		});
		Resource res = resourceSet.getResource(URI.createURI("file:/1.importuritestlanguage"), true);
		assertEquals(res.getErrors().toString(), 1, res.getErrors().size());
	}

	public void testRedundantImport() {
		XtextResourceSet resourceSet = get(XtextResourceSet.class);
		resourceSet.setClasspathURIContext(getClass().getClassLoader());
		URI uri = URI.createURI("classpath:/org/eclipse/xtext/crossrefs/03.importuritestlanguage");
		Resource res = resourceSet.getResource(uri, true);
		assertNotNull("res", res);
		assertFalse(res.getErrors().toString(), res.getErrors().isEmpty());
	}

	public void _testRedundantImportLarge() {
		XtextResourceSet resourceSet = get(XtextResourceSet.class);
		resourceSet.setClasspathURIContext(getClass().getClassLoader());
		URI uri = URI.createURI("classpath:/org/eclipse/xtext/crossrefs/04.importuritestlanguage");
		Resource res = resourceSet.getResource(uri, true);
		assertNotNull("res", res);
		assertFalse(res.getErrors().toString(), res.getErrors().isEmpty());
	}

}
