package com.lesein.common.base.generator;

import com.google.common.collect.ImmutableMap;
import com.lesein.common.base.generator.config.DataSourceConfig;
import com.lesein.common.base.generator.config.GlobalConfig;
import com.lesein.common.base.generator.config.PackageConfig;
import com.lesein.common.base.generator.config.StrategyConfig;
import com.lesein.common.base.generator.config.converts.ITypeHandler;
import com.lesein.common.base.generator.config.converts.MySqlTypeConvert;
import com.lesein.common.base.generator.config.rules.IColumnType;
import com.lesein.common.base.generator.config.type.DbType;
import com.lesein.common.base.persistence.BaseMapper;
import com.lesein.common.base.persistence.BaseService;
import com.lesein.common.base.request.BaseRequest;

import java.util.Map;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class CodeGeneratorDemo {
    /**
     * 作者
     */
    private static final String author = "WangJie";
    /**
     * 项目位置
     */
    private static final String projectPath = "F:\\GIT\\Projects\\sakeray\\common";
    /**
     * 生成类文件存放位置
     */
    private static final String classOutPath = projectPath + "/src/main/java";
    /**
     * 生成xxxMapper.xml文件存放位置
     */
    private static final String mapperXmlPath = projectPath + "/src/main/resources/META-INF/mapping";
    /**
     * 模块结构（模块路径）
     */
    private static final String modulePath = "com.lesein.common";

    /**
     * 模块名
     */
    private static final String moduleName = "login";

    /**
     * 指定表，支持正则批量生成
     */
    private static final String[] tableNames = {
            "user_account"
    };

    /**
     * 指定表和实体名称，如果两者都填写，默认优先取上面数组
     */
    private static final Map<String, String> tableMap = ImmutableMap.<String, String>builder()
            .build();

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(classOutPath);
        gc.setAuthor(author);
        //是否覆盖旧文件
        gc.setFileOverride(true);
        //生成之后是否打开文件夹
        gc.setOpen(false);
        //如果开启，必须填写接口文档token
        gc.setyApiToken("");
        gc.setProjectModuleCode("hello");
        gc.setRequestAddOrUpdateEnable(false);

        // 自定义文件命名，注意 %s 会自动填充表实体属性！默认可以不填写
        gc.setRequestAddName("%sRequestAdd");
        gc.setRequestUpdateName("%sRequestUpd");
        gc.setRequestName("%sRequest");
        gc.setRequestSearchName("%sRequestSearch");
        gc.setDtoName("%sDTO");
        gc.setXmlName("%sMapper");
        gc.setDaoName("%sMapper");
        gc.setServiceName("%sService");
        gc.setApiName("%sApi");
        gc.setProviderName("%sApiImpl");
        gc.setJunitName("%sJunit");
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig().setUrl("jdbc:mysql://localhost:3306/sakeray_authorization")
                .setDriverName("com.mysql.jdbc.Driver")
                .setUsername("root")
                .setPassword("sakeray")
                .setDbType(DbType.MYSQL)
                .setTypeConvert(new MySqlTypeConvert(){

                    @Override
                    public IColumnType customerType(String fieldName, String fieldType) {
                        //支持针对某个特定字段进行实体类型转换，例如数据库类型为int，实体为Date
                        return null;
                    }
                });
        mpg.setDataSource(dsc);

        // 包路径配置，支持自定义各个包名，默认可以不填写
        PackageConfig pc = new PackageConfig();
        pc.setParent(modulePath);
        pc.setModuleName(moduleName);
        pc.setEntity("entity");
        pc.setRequest("request");
        pc.setDto("dto");
        pc.setXml("META-INF.mapper");
        pc.setDao("dao");
        pc.setService("service");
        pc.setApi("api");
        pc.setProvider("impl");
        mpg.setPackageInfo(pc);

        // 实体生成策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setSuperEntityClass(null);
        strategy.setSuperDtoClass(null);
        strategy.setSuperRequestClass(BaseRequest.class.getCanonicalName());
        strategy.setSuperDaoClass(BaseMapper.class.getCanonicalName());
        strategy.setSuperServiceClass(BaseService.class.getCanonicalName());
        strategy.setTablePrefix("ts_", "tb_", "td_");
        strategy.setIncludeTable(tableNames);
        strategy.setTableMap(tableMap);
        strategy.setTypeHandler(new ITypeHandler(){

            @Override
            public boolean enable(String fieldName) {
                //是否开启某个字段mybatis类型适配转换
                return false;
            }

            @Override
            public String processTypeHandler(String fieldName) {
                //返回需要转换的类名（含包路径）
                return null;
            }
        });
        mpg.setStrategy(strategy);
        mpg.execute();
    }
}
