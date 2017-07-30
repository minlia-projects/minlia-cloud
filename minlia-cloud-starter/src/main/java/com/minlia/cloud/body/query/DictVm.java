package com.minlia.cloud.body.query;

import lombok.Data;

@Data
public class DictVm {

    public static final String F_CODE = "code";
    public static final String F_NAME = "name";
    public static final String F_VAL = "val";
    /*** 名称 */
    private String name;
    /*** 编码 */
    private String code;
    /*** 字典值 */
    private String val;

    public DictVm() {
        super();
    }

    public DictVm(String name, String code, String val) {
        super();
        this.name = name;
        this.code = code;
        this.val = val;
    }

}