/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.cdi.api.test;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.spi.ManagedContext;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;

/**
 * @author John D. Ament
 */
public class ContextManagementExample {
    public void startAContext() {
        BeanManager beanManager = CDI.current().getBeanManager();
        try(ManagedContext requestContext = beanManager.activateContext(RequestScoped.class)) {
            SomeBean someBean = CDI.current().select(SomeBean.class).get();
            someBean.doWork();
        }
    }

    @RequestScoped
    private static final class SomeBean {
        String doWork() {
            return "work";
        }
    }
}
