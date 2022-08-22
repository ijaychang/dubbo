/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.dubbo.common.extensionloader;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.alibaba.dubbo.common.extensionloader.compatible.CompatibleExt;
import com.alibaba.dubbo.common.extensionloader.compatible.impl.CompatibleExtImpl1;
import com.alibaba.dubbo.common.extensionloader.compatible.impl.CompatibleExtImpl2;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ExtensionLoader_Compatible_Test {

    @Test
    public void test_getExtension() throws Exception {
        // com.alibaba.dubbo.common.extensionloader.compatible.CompatibleExt 文件内允许没有query name
        // 如果没有query name，那么会去看实现类的@Extension注解，取出value值，作为query name，不过@Extension已被废弃，所以也不建议这么做了
        assertTrue(ExtensionLoader.getExtensionLoader(CompatibleExt.class).getExtension("impl1") instanceof CompatibleExtImpl1);
        assertTrue(ExtensionLoader.getExtensionLoader(CompatibleExt.class).getExtension("impl2") instanceof CompatibleExtImpl2);
    }
}