/*******************************************************************************
 * Copyright (c) 2015 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.xtext.web.server.validation

import java.util.ArrayList
import org.eclipse.xtend.lib.annotations.Accessors
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.ToString
import org.eclipse.xtext.web.server.IServiceResult

/**
 * Result object returned by the validation service.
 */
@Accessors
@ToString(skipNulls = true)
class ValidationResult implements IServiceResult {
	
	val entries = new ArrayList<Entry>
	
	@Data
	@ToString(skipNulls = true)
	static class Entry {
		String description
		String severity 
		Integer line
		Integer startOffset
		Integer endOffset
	}
	
}
