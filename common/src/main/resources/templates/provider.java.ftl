package ${package.provider};

import org.springframework.beans.factory.annotation.Autowired;
import com.lesein.common.base.aop.RestConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.lesein.common.base.response.BaseResponse;
import com.lesein.common.base.request.IdRequest;
import com.lesein.common.base.util.PageUtil;
import java.util.List;

import ${package.api}.${apiClass};
import ${package.service}.${serviceClass};
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
 * ${entityClass} Api实现层
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestConfiguration
@RequestMapping("/${apiClass}")
@RestController
public class ${providerClass} implements ${apiClass} {

	@Autowired
	private ${serviceClass} ${serviceClass?uncap_first};

	@Override
	@PostMapping("/selectPage")
	public BaseResponse<PageUtil<${dtoClass}>> selectPage(@RequestBody ${requestSearchClass} request){
		PageUtil<${dtoClass}> pageUtils = ${serviceClass?uncap_first}.selectPage(request);
		return BaseResponse.setSuccessResponse(pageUtils);
	}

	@Override
	@PostMapping("/selectAll")
	public BaseResponse<List<${dtoClass}>> selectAll(@RequestBody ${requestSearchClass} request){
		List<${dtoClass}> resultList = ${serviceClass?uncap_first}.selectAll(request);
		return BaseResponse.setSuccessResponse(resultList);
	}

	@Override
	@PostMapping("/getDetail")
	public BaseResponse<${dtoClass}> getDetail(@RequestBody IdRequest request){
		${dtoClass} result = ${serviceClass?uncap_first}.getDetail(request);
		return BaseResponse.setSuccessResponse(result);
	}

	@Override
	@PostMapping("/add")
	<#if isRequestAddOrUpdateEnable>
	public BaseResponse<Void> add(@RequestBody ${requestAddClass} request){
	<#else>
	public BaseResponse<Void> add(@RequestBody ${requestClass} request){
	</#if>
		${serviceClass?uncap_first}.add(request);
		return BaseResponse.setSuccessResponse();
	}

	@Override
	@PostMapping("/edit")
	<#if isRequestAddOrUpdateEnable>
	public BaseResponse<Void> edit(@RequestBody ${requestUpdateClass} request){
	<#else>
	public BaseResponse<Void> edit(@RequestBody ${requestClass} request){
	</#if>
		${serviceClass?uncap_first}.update(request);
		return BaseResponse.setSuccessResponse();
	}

	@Override
	@PostMapping("/delete")
	public BaseResponse<Void> delete(@RequestBody IdRequest request){
		${serviceClass?uncap_first}.delete(request);
		return BaseResponse.setSuccessResponse();
	}
}
