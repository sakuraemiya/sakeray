package ${package.api};

import com.lesein.common.base.response.BaseResponse;
import com.lesein.common.base.request.IdRequest;
import com.lesein.common.base.util.PageUtil;
import java.util.List;
import ${package.request}.${requestSearchClass};
<#if isRequestAddOrUpdateEnable>
import ${package.request}.${requestAddClass};
import ${package.request}.${requestUpdateClass};
<#else>
import ${package.request}.${requestClass};
</#if>
import ${package.dto}.${dtoClass};

/**
 * <p>
 * ${entityClass} Api层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${apiClass} {

	/**
	 * 分页列表
	 * @param request
	 * @return
	 */
	BaseResponse<PageUtil<${dtoClass}>> selectPage(${requestSearchClass} request);

	/**
	 * 查询数据集（下拉框）
	 * @param request
	 * @return
	 */
	BaseResponse<List<${dtoClass}>> selectAll(${requestSearchClass} request);

	/**
	 * 查询详情
	 * @param request
	 * @return
	 */
	BaseResponse<${dtoClass}> getDetail(IdRequest request);

	/**
	 * 新增操作
	 * @param request
	 * @return
	 */
	<#if isRequestAddOrUpdateEnable>
	BaseResponse<Void> add(${requestAddClass} request);
	<#else>
	BaseResponse<Void> add(${requestClass} request);
	</#if>

	/**
	 * 编辑操作
	 * @param request
	 * @return
	 */
	<#if isRequestAddOrUpdateEnable>
	BaseResponse<Void> edit(${requestUpdateClass} request);
	<#else>
	BaseResponse<Void> edit(${requestClass} request);
	</#if>

	/**
	 * 删除操作
	 * @param request
     * @return
	 */
	BaseResponse<Void> delete(IdRequest request);
}
