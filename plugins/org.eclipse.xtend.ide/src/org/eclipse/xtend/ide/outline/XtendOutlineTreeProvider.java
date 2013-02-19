/*
* generated by Xtext
*/
package org.eclipse.xtend.ide.outline;

import static com.google.common.collect.Iterables.*;
import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Sets.*;

import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.eclipse.xtend.core.dispatch.DispatchingSupport;
import org.eclipse.xtend.core.jvmmodel.IXtendJvmAssociations;
import org.eclipse.xtend.core.xtend.XtendClass;
import org.eclipse.xtend.core.xtend.XtendField;
import org.eclipse.xtend.core.xtend.XtendFile;
import org.eclipse.xtend.core.xtend.XtendFunction;
import org.eclipse.xtend.core.xtend.XtendMember;
import org.eclipse.xtend.core.xtend.XtendPackage;
import org.eclipse.xtend.core.xtend.XtendTypeDeclaration;
import org.eclipse.xtend.ide.labeling.XtendImages;
import org.eclipse.xtext.common.types.JvmDeclaredType;
import org.eclipse.xtext.common.types.JvmFeature;
import org.eclipse.xtext.common.types.JvmField;
import org.eclipse.xtext.common.types.JvmMember;
import org.eclipse.xtext.common.types.JvmOperation;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.common.types.JvmVisibility;
import org.eclipse.xtext.common.types.util.FeatureOverridesService;
import org.eclipse.xtext.common.types.util.SuperTypeCollector;
import org.eclipse.xtext.common.types.util.TypeReferences;
import org.eclipse.xtext.common.types.util.VisibilityService;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DocumentRootNode;
import org.eclipse.xtext.ui.editor.outline.impl.EObjectNode;
import org.eclipse.xtext.ui.editor.outline.impl.ModeAwareOutlineTreeProvider;
import org.eclipse.xtext.ui.editor.outline.impl.OutlineMode;
import org.eclipse.xtext.util.TextRegion;
import org.eclipse.xtext.xbase.jvmmodel.JvmTypeExtensions;
import org.eclipse.xtext.xtype.XImportDeclaration;
import org.eclipse.xtext.xtype.XtypePackage;

import com.google.common.collect.Multimap;
import com.google.inject.Inject;

/**
 * Customization of the default outline structure.
 * 
 * @author Jan Koehnlein
 */
@SuppressWarnings("deprecation")
public class XtendOutlineTreeProvider extends ModeAwareOutlineTreeProvider {

	private static final OutlineMode SHOW_INHERITED_MODE = new OutlineMode("show", "show inherited members");

	private static final OutlineMode HIDE_INHERITED_MODE = new OutlineMode("hide", "hide inherited members");

	private static final List<OutlineMode> MODES = newArrayList(HIDE_INHERITED_MODE, SHOW_INHERITED_MODE);

	@Inject
	private XtendImages images;

	@Inject
	private IXtendJvmAssociations associations;

	@Inject
	private DispatchingSupport dispatchingSupport;

	@Inject
	private FeatureOverridesService featureOverridesService;

	@Inject
	private TypeReferences typeReferences;

	@Inject
	private VisibilityService visibilityService;
	
	@Inject
	private JvmTypeExtensions typeExtensions;
	
	@Inject
	private SuperTypeCollector superTypeCollector;

	protected void _createChildren(DocumentRootNode parentNode, XtendFile xtendFile) {
		if (xtendFile.getPackage() != null)
			createEStructuralFeatureNode(parentNode, xtendFile, XtendPackage.Literals.XTEND_FILE__PACKAGE,
					images.forPackage(), xtendFile.getPackage(), true);
		if (xtendFile.getImportSection() != null && !xtendFile.getImportSection().getImportDeclarations().isEmpty())
			createEStructuralFeatureNode(parentNode, xtendFile.getImportSection(), XtypePackage.Literals.XIMPORT_SECTION__IMPORT_DECLARATIONS,
					images.forImportContainer(), "import declarations", false);
		for (XtendTypeDeclaration xtendType : xtendFile.getXtendTypes()) {
			EObjectNode classNode = createEObjectNode(parentNode, xtendType);
			createFeatureNodes(classNode, xtendType);
		}
	}

	protected void createFeatureNodes(IOutlineNode parentNode, XtendTypeDeclaration xtendType) {
		final JvmDeclaredType inferredType = associations.getInferredType(xtendType);
		if (inferredType != null) {
			Set<JvmFeature> processedFeatures = newHashSet();
			if(xtendType instanceof XtendClass) {
				Multimap<JvmOperation, JvmOperation> dispatcher2dispatched = dispatchingSupport.getDispatcher2dispatched(
						(XtendClass) xtendType, getCurrentMode() == HIDE_INHERITED_MODE);
				for (JvmOperation dispatcher : dispatcher2dispatched.keySet()) {
					XtendFeatureNode dispatcherNode = createNodeForFeature(parentNode, inferredType, dispatcher, dispatcher);
					if (dispatcherNode != null) {
						dispatcherNode.setDispatch(true);
						processedFeatures.add(dispatcher);
						for (JvmOperation dispatchCase : dispatcher2dispatched.get(dispatcher)) {
							XtendFunction xtendFunction = associations.getXtendFunction(dispatchCase);
							if (xtendFunction == null) {
								createNodeForFeature(dispatcherNode, inferredType, dispatchCase, dispatchCase);
							} else {
								createNodeForFeature(dispatcherNode, inferredType, dispatchCase, xtendFunction);
							}
							processedFeatures.add(dispatchCase);
						}
					}
				}
			}
			List<JvmMember> remainingFeatures = newArrayList();
			remainingFeatures.addAll(inferredType.getMembers());
			if (getCurrentMode() == SHOW_INHERITED_MODE) {
				Set<JvmTypeReference> superTypes = superTypeCollector.collectSuperTypes(inferredType);
				for (JvmTypeReference superType : superTypes) {
					JvmDeclaredType type = (JvmDeclaredType) superType.getType();
					for (JvmMember member : type.getMembers()) {
						if (member.getVisibility() != JvmVisibility.PRIVATE)
							remainingFeatures.add(member);
					}
				}
			} 
			for (JvmFeature feature : filter(remainingFeatures, JvmFeature.class)) {
				if (!processedFeatures.contains(feature)) {
					EObject primarySourceElement = associations.getPrimarySourceElement(feature);
					createNodeForFeature(parentNode, inferredType, feature, 
							primarySourceElement != null ? primarySourceElement : feature);
				}
			}
		} else {
			for (XtendMember member : xtendType.getMembers())
				createEObjectNode(parentNode, member);
		}
	}

	protected XtendFeatureNode createNodeForFeature(IOutlineNode parentNode, final JvmDeclaredType inferredType,
			JvmFeature jvmFeature, EObject semanticFeature) {
		Object text = textDispatcher.invoke(semanticFeature);
		Image image = imageDispatcher.invoke(semanticFeature);
		final boolean synthetic = typeExtensions.isSynthetic(jvmFeature);
		if (synthetic) {
			text = textDispatcher.invoke(jvmFeature);
			image = imageDispatcher.invoke(jvmFeature);
		}
		if (jvmFeature.getDeclaringType() != inferredType) {
			if (getCurrentMode() == SHOW_INHERITED_MODE) {
				StyledString label = (text instanceof StyledString) ? (StyledString) text : new StyledString(
						text.toString());
				label.append(new StyledString(" - " + jvmFeature.getDeclaringType().getIdentifier(),
						StyledString.COUNTER_STYLER));
				return createXtendFeatureNode(parentNode, jvmFeature, image, label, true, synthetic);
			}
			return null;
		} else {
			return createXtendFeatureNode(parentNode, semanticFeature, image, text, true, synthetic);
		}
	}
	
	protected XtendFeatureNode createXtendFeatureNode(IOutlineNode parentNode, EObject modelElement, Image image, Object text,
			boolean isLeaf, boolean synthetic) {
		XtendFeatureNode featureNode = new XtendFeatureNode(modelElement, parentNode, image, text, isLeaf);
		ICompositeNode parserNode = NodeModelUtils.getNode(modelElement);
		if (parserNode != null)
			featureNode.setTextRegion(new TextRegion(parserNode.getOffset(), parserNode.getLength()));
		if(isLocalElement(parentNode, modelElement))
			featureNode.setShortTextRegion(locationInFileProvider.getSignificantTextRegion(modelElement));
		featureNode.setStatic(isStatic(modelElement));
		featureNode.setSynthetic(synthetic);
		return featureNode;
	}

	protected boolean isStatic(EObject element) {
		if (element instanceof JvmField) 
			return  ((JvmField) element).isStatic();
		else if(element instanceof JvmOperation) 
			return  ((JvmOperation) element).isStatic();
		else if(element instanceof XtendField) 
			return  ((XtendField) element).isStatic();
		else if(element instanceof XtendFunction) 
			return  ((XtendFunction) element).isStatic();
		else return false;
	}
	
	@Override
	protected boolean _isLeaf(EObject element) {
		return true;
	}

	protected Object _text(XImportDeclaration importDeclaration) {
		return (importDeclaration.getImportedNamespace() != null) ? importDeclaration.getImportedNamespace() : importDeclaration
				.getImportedTypeName();
	}

	@Override
	public List<OutlineMode> getOutlineModes() {
		return MODES;
	}
}
