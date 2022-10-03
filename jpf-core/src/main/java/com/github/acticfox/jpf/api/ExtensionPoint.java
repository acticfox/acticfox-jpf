/*
 * Copyright (C) 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.github.acticfox.jpf.api;

/**
 * An extension point is a formal declaration in a plugin (or in application API) where customization is allowed.
 * 扩展点表示一块逻辑在不同的业务有不同的实现，使用扩展点做接口申明，扩展点定义为ExtPt为结尾，然后用Extension（扩展）去实现扩展点。
 * 
 * @author Decebal Suiu
 */
public interface ExtensionPoint {

}
