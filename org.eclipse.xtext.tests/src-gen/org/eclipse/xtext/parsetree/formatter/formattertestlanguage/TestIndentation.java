/**
 * Copyright (c) 2010, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtext.parsetree.formatter.formattertestlanguage;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Test Indentation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.xtext.parsetree.formatter.formattertestlanguage.TestIndentation#getItems <em>Items</em>}</li>
 *   <li>{@link org.eclipse.xtext.parsetree.formatter.formattertestlanguage.TestIndentation#isSemi <em>Semi</em>}</li>
 * </ul>
 *
 * @see org.eclipse.xtext.parsetree.formatter.formattertestlanguage.FormattertestlanguagePackage#getTestIndentation()
 * @model
 * @generated
 */
public interface TestIndentation extends Root
{
  /**
   * Returns the value of the '<em><b>Items</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Items</em>' containment reference list.
   * @see org.eclipse.xtext.parsetree.formatter.formattertestlanguage.FormattertestlanguagePackage#getTestIndentation_Items()
   * @model containment="true"
   * @generated
   */
  EList<EObject> getItems();

  /**
   * Returns the value of the '<em><b>Semi</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Semi</em>' attribute.
   * @see #setSemi(boolean)
   * @see org.eclipse.xtext.parsetree.formatter.formattertestlanguage.FormattertestlanguagePackage#getTestIndentation_Semi()
   * @model
   * @generated
   */
  boolean isSemi();

  /**
   * Sets the value of the '{@link org.eclipse.xtext.parsetree.formatter.formattertestlanguage.TestIndentation#isSemi <em>Semi</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Semi</em>' attribute.
   * @see #isSemi()
   * @generated
   */
  void setSemi(boolean value);

} // TestIndentation
