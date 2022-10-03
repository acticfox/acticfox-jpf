package com.github.actic.extension.register;

import org.springframework.stereotype.Component;

import com.github.acticfox.jpf.api.Extension;

@Extension(tenantId = "A")
@Component
public class SomeExtensionA implements SomeExtPt {

    @Override
    public void doSomeThing() {
        System.out.println("SomeExtensionA::doSomething");
    }

}