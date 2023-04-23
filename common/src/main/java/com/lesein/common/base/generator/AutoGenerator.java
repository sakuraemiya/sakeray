package com.lesein.common.base.generator;

import com.lesein.common.base.generator.builder.ConfigBuilder;
import com.lesein.common.base.generator.config.DataSourceConfig;
import com.lesein.common.base.generator.config.GlobalConfig;
import com.lesein.common.base.generator.config.PackageConfig;
import com.lesein.common.base.generator.config.StrategyConfig;
import com.lesein.common.base.generator.engine.TemplateEngine;
import com.lesein.common.base.util.ParamValidatorUtil;

/**
 * @author WangJie
 * @date 2023/4/23
 */
public class AutoGenerator {
    /**
     * 配置信息
     */
    protected ConfigBuilder config;

    /**
     * 全局 相关配置
     */
    private GlobalConfig globalConfig;

    /**
     * 数据源配置
     */
    private DataSourceConfig dataSource;

    /**
     * 包相关配置
     */
    private PackageConfig packageInfo;

    /**
     * 数据库表配置
     */
    private StrategyConfig strategy;

    /**
     * 初始化模版引擎
     */
    private TemplateEngine templateEngine;


    /**
     * 生成代码
     */
    public void execute() {
        System.out.println("==========================准备生成文件...==========================");
        validateData();
        // 初始化配置
        if (null == config) {
            config = new ConfigBuilder(globalConfig, dataSource, packageInfo, strategy);
        }
        if (null == templateEngine) {
            templateEngine = new TemplateEngine();
        }
        // 模板引擎初始化执行文件输出
        templateEngine.init(config).mkdirs().batchOutput().open();
        System.out.println("==========================文件生成完成！！！==========================");
    }

    /**
     * 参数验证
     */
    private void validateData(){
        //全局配置校验
        ParamValidatorUtil.checkNotNull(globalConfig, "全局配置不能为空");
        ParamValidatorUtil.checkNotNull(globalConfig.getOutputDir(), "全局配置【outputDir】不能为空");
        ParamValidatorUtil.checkNotNull(globalConfig.getAuthor(), "全局配置【author】不能为空");
        ParamValidatorUtil.checkNotNull(globalConfig.getProjectModuleCode(), "全局配置【projectModuleCode】不能为空");
        //数据源配置校验
        ParamValidatorUtil.checkNotNull(dataSource, "数据源配置不能为空");
        ParamValidatorUtil.checkNotNull(dataSource.getUrl(), "数据源配置【URL】不能为空");
        ParamValidatorUtil.checkNotNull(dataSource.getDriverName(), "数据源配置【driverName】不能为空");
        ParamValidatorUtil.checkNotNull(dataSource.getUsername(), "数据源配置【userName】不能为空");
        ParamValidatorUtil.checkNotNull(dataSource.getPassword(), "数据源配置【password】不能为空");
        ParamValidatorUtil.checkNotNull(dataSource.getDbType(), "数据源配置【dbType】不能为空");
        //包路径配置校验
        ParamValidatorUtil.checkNotNull(packageInfo, "包配置不能为空");
        ParamValidatorUtil.checkNotNull(packageInfo.getParent(), "包配置【parent】不能为空");
        //实体生成策略配置校验
        ParamValidatorUtil.checkNotNull(strategy, "实体生成策略配置不能为空");
    }


    // ==================================  相关配置  ==================================


    public ConfigBuilder getConfig() {
        return config;
    }

    public AutoGenerator setConfig(ConfigBuilder config) {
        this.config = config;
        return this;
    }

    public GlobalConfig getGlobalConfig() {
        return globalConfig;
    }

    public AutoGenerator setGlobalConfig(GlobalConfig globalConfig) {
        this.globalConfig = globalConfig;
        return this;
    }

    public DataSourceConfig getDataSource() {
        return dataSource;
    }

    public AutoGenerator setDataSource(DataSourceConfig dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public PackageConfig getPackageInfo() {
        return packageInfo;
    }

    public AutoGenerator setPackageInfo(PackageConfig packageInfo) {
        this.packageInfo = packageInfo;
        return this;
    }

    public StrategyConfig getStrategy() {
        return strategy;
    }

    public AutoGenerator setStrategy(StrategyConfig strategy) {
        this.strategy = strategy;
        return this;
    }

    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }

    public AutoGenerator setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        return this;
    }
}
