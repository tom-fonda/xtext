/**
 * Copyright (c) 2013, 2024 itemis AG (http://www.itemis.eu) and others.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 * 
 * SPDX-License-Identifier: EPL-2.0
 */
package org.eclipse.xtend.core.tests.compiler;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.Test;

/**
 * @author Sebastian Zarnekow - Initial contribution and API
 */
@SuppressWarnings("all")
public class CompilerBug419050Test extends AbstractXtendCompilerTest {
  @Test
  public void test_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def boolean m(boolean b) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (b) { ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return true ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (b) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return true;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return false;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_02() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def boolean m1(boolean x) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("while(x) {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return true");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m2(boolean x) {");
    _builder.newLine();
    _builder.append("\t  ");
    _builder.append("while(x) {");
    _builder.newLine();
    _builder.append("\t    ");
    _builder.append("return true");
    _builder.newLine();
    _builder.append("\t  ");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean m1(final boolean x) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("while (x) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return true;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return false;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Boolean m2(final boolean x) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("while (x) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return Boolean.valueOf(true);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_03() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def boolean m(Object o) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("switch o { ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("String: return true ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean m(final Object o) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("boolean _matched = false;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (o instanceof String) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_matched=true;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return true;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return false;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_04() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def boolean m(Object o) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("try {");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return true;");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("} finally {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean m(final Object o) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("try {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return true;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("} finally {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_05() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(boolean b) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("if (b) { ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("while(b) {}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (b) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("while (b) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_06() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(boolean b) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("var boolean y = if (b) return true");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Boolean m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("boolean _xifexpression = false;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (b) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return Boolean.valueOf(true);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("boolean y = _xifexpression;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_07() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(boolean b) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val boolean x = if (b) true");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public void m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("boolean _xifexpression = false;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("if (b) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("_xifexpression = true;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final boolean x = _xifexpression;");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_08() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(boolean b) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("while(true) return false");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Boolean m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("while (true) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return Boolean.valueOf(false);");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_09() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def boolean m(boolean b) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("while(true) return false");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("while (true) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return false;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_10() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def boolean m(boolean b) { ");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val func = [String s|while(true) return false]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("while(true) ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return func.apply(\"foo\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Boolean> _function = (String s) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("while (true) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Boolean.valueOf(false);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Boolean> func = _function;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("while (true) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return (func.apply(\"foo\")).booleanValue();");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void test_11() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def boolean m(boolean b) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("val com.google.common.base.Predicate<String> func = [while(true) return false]");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("while(true) ");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("return func.apply(\"foo\")");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import com.google.common.base.Predicate;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public boolean m(final boolean b) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Predicate<String> _function = (String it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("while (true) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return false;");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Predicate<String> func = _function;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("while (true) {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return func.apply(\"foo\");");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }

  @Test
  public void testBug417371_01() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("class C {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("def m(Iterable<String> iter) {");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("iter.filter [");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("if (length < 2) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return true");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("} else if (length > 2) {");
    _builder.newLine();
    _builder.append("\t\t\t\t");
    _builder.append("return false");
    _builder.newLine();
    _builder.append("\t\t\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("\t\t");
    _builder.append("] ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("}");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    StringConcatenation _builder_1 = new StringConcatenation();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.Functions.Function1;");
    _builder_1.newLine();
    _builder_1.append("import org.eclipse.xtext.xbase.lib.IterableExtensions;");
    _builder_1.newLine();
    _builder_1.newLine();
    _builder_1.append("@SuppressWarnings(\"all\")");
    _builder_1.newLine();
    _builder_1.append("public class C {");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("public Iterable<String> m(final Iterable<String> iter) {");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("final Function1<String, Boolean> _function = (String it) -> {");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("int _length = it.length();");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("boolean _lessThan = (_length < 2);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("if (_lessThan) {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("return Boolean.valueOf(true);");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("} else {");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("int _length_1 = it.length();");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("boolean _greaterThan = (_length_1 > 2);");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("if (_greaterThan) {");
    _builder_1.newLine();
    _builder_1.append("          ");
    _builder_1.append("return Boolean.valueOf(false);");
    _builder_1.newLine();
    _builder_1.append("        ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("      ");
    _builder_1.append("return null;");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("};");
    _builder_1.newLine();
    _builder_1.append("    ");
    _builder_1.append("return IterableExtensions.<String>filter(iter, _function);");
    _builder_1.newLine();
    _builder_1.append("  ");
    _builder_1.append("}");
    _builder_1.newLine();
    _builder_1.append("}");
    _builder_1.newLine();
    this.assertCompilesTo(_builder, _builder_1);
  }
}
