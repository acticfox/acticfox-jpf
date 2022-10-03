package com.github.acticfox.jpf.api;

/**
 * BizScenario（业务场景）= tenantId + useCase + scenario, which can uniquely identify a user scenario.
 *
 * @author kfy Jun 20, 2022 2:00:19 PM
 * @version V1.0
 */
public class BizScenario {
    public final static String DEFAULT_TENANT_ID = "0";

    /**
     * tenantId is used to identify a business
     */
    private String tenantId = DEFAULT_TENANT_ID;

    /**
     * For above case, the BizScenario will be "tmall.placeOrder.88vip", with this code, we can provide extension
     * processing other than "tmall.placeOrder.normal" scenario.
     *
     * @return
     */
    public String getUniqueIdentity() {
        return tenantId;
    }

    public static BizScenario valueOf(String tenantId) {
        BizScenario bizScenario = new BizScenario();
        if (tenantId != null && !"".equals(tenantId)) {
            bizScenario.tenantId = tenantId;
        }

        return bizScenario;
    }

}
