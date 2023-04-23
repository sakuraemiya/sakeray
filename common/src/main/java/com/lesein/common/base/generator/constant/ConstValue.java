package com.lesein.common.base.generator.constant;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class ConstValue {
    // ************************ 全局配置常量 *****************************

    public static final String GLOBAL_REQUEST = "Request";
    public static final String GLOBAL_REQUEST_ADD = "RequestAdd";
    public static final String GLOBAL_REQUEST_UPDATE = "RequestUpd";
    public static final String GLOBAL_REQUEST_SEARCH = "RequestSearch";
    public static final String GLOBAL_DTO = "DTO";
    public static final String GLOBAL_XML = "Mapper";
    public static final String GLOBAL_DAO = "Mapper";
    public static final String GLOBAL_SERVICE = "Service";
    public static final String GLOBAL_API = "Api";
    public static final String GLOBAL_PROVIDER = "ApiImpl";


    // ************************ 包配置常量key *****************************

    public static final String PACKAGE_ENTITY = "entity";
    public static final String PACKAGE_REQUEST = "request";
    public static final String PACKAGE_DTO = "dto";
    public static final String PACKAGE_DAO = "dao";
    public static final String PACKAGE_SERVICE = "service";
    public static final String PACKAGE_API = "api";
    public static final String PACKAGE_PROVIDER = "provider";
    public static final String PACKAGE_XML = "xml";

    // ************************ 文件路径配置常量key *****************************

    public static final String ENTITY_PATH = "entity_path";
    public static final String REQUEST_PATH = "request_path";
    public static final String DTO_PATH = "dto_path";
    public static final String XML_PATH = "xml_path";
    public static final String DAO_PATH = "dao_path";
    public static final String SERVICE_PATH = "service_path";
    public static final String API_PATH = "api_path";
    public static final String PROVIDER_PATH = "provider_path";


    // ************************ 模版配置后缀常量 *****************************

    public static final String TEMPLATE_JAVA_SUFFIX = "%s.java";
    public static final String TEMPLATE_XML_SUFFIX = "%s.xml";


    // ************************ 模版配置后缀常量 *****************************

    public static final String TEMPLATE_ENTITY = "/templates/entity.java.ftl";
    public static final String TEMPLATE_REQUEST = "/templates/request.java.ftl";
    public static final String TEMPLATE_REQUEST_ADD = "/templates/requestAdd.java.ftl";
    public static final String TEMPLATE_REQUEST_UPDATE = "/templates/requestUpd.java.ftl";
    public static final String TEMPLATE_REQUEST_SEARCH = "/templates/requestSearch.java.ftl";
    public static final String TEMPLATE_DTO = "/templates/dto.java.ftl";
    public static final String TEMPLATE_XML = "/templates/mapper.xml.ftl";
    public static final String TEMPLATE_DAO = "/templates/dao.java.ftl";
    public static final String TEMPLATE_SERVICE = "/templates/service.java.ftl";
    public static final String TEMPLATE_API = "/templates/api.java.ftl";
    public static final String TEMPLATE_PROVIDER = "/templates/provider.java.ftl";


    // ************************ 其他配置常量 *****************************

    public static final String TABLE_PRIMARY = "id";
    public static final String JAVA_TMPDIR = "java.io.tmpdir";
}
