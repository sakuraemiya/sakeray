package ${package.request};

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

<#list fieldsImportPackage as importPackage>
import ${importPackage};
</#list>

import ${superRequestClassPackage};


/**
 * <p>
 * ${tableComment}
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public class ${requestClass} extends ${superRequestClass} implements Serializable {

	private static final long serialVersionUID = 1L;

	<#--属性遍历-->
	<#list columns as pro>
	<#if pro.propertyName != 'isDelete'
	&& pro.propertyName != 'createUserid'
	&& pro.propertyName != 'createUserId'
	&& pro.propertyName != 'createUserName'
	&& pro.propertyName != 'createTime'
	&& pro.propertyName != 'createtime'
	&& pro.propertyName != 'updateUserid'
	&& pro.propertyName != 'updateUserId'
	&& pro.propertyName != 'updateUserName'
	&& pro.propertyName != 'updateTime'
	&& pro.propertyName != 'lastupdatetime'
	>

	/**
	 * ${pro.comment}
	 */
	<#if pro.propertyName != primaryId && pro.propertyType == 'String'>
	@NotBlank(message = "${pro.comment}" + notBlankMsg)
    <#if (pro.fieldLength < 1000)>
	@Size(max = ${pro.fieldLength}, message = "${pro.comment}最大长度不得超过${pro.fieldLength}")
    </#if>
    <#elseif pro.propertyName != primaryId && pro.propertyType != 'String'>
	@NotNull(message = "${pro.comment}" + notBlankMsg)
    <#else>
	</#if>
	private ${pro.propertyType} ${pro.propertyName};
    </#if>
	</#list>

	<#--属性get||set方法-->
	<#list columns as pro>
	<#if pro.propertyName != 'isDelete'
	&& pro.propertyName != 'createUserid'
	&& pro.propertyName != 'createUserId'
	&& pro.propertyName != 'createUserName'
	&& pro.propertyName != 'createTime'
	&& pro.propertyName != 'createtime'
	&& pro.propertyName != 'updateUserid'
	&& pro.propertyName != 'updateUserId'
	&& pro.propertyName != 'updateUserName'
	&& pro.propertyName != 'updateTime'
	&& pro.propertyName != 'lastupdatetime'
	>
	public ${pro.propertyType} get${pro.propertyName?cap_first}() {
		return this.${pro.propertyName};
	}

	public ${requestClass} set${pro.propertyName?cap_first}(${pro.propertyType} ${pro.propertyName}) {
        <#if pro.propertyType == 'String'>
		this.${pro.propertyName} = ${pro.propertyName} == null ? null : ${pro.propertyName}.trim();
        <#else>
		this.${pro.propertyName} = ${pro.propertyName};
        </#if>
		return this;
	}
	</#if>
	</#list>
}