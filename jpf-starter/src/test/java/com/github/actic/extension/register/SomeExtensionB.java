package com.github.actic.extension.register;

import org.springframework.stereotype.Component;

import com.github.acticfox.jpf.api.Extension;

@Extension(tenantId = "B")
@Component
public class SomeExtensionB implements SomeExtPt {

    @Override
    public void doSomeThing() {
        System.out.println("SomeExtensionB::doSomething");
    }

}
