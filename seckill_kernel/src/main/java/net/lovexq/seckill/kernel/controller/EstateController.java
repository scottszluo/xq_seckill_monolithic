package net.lovexq.seckill.kernel.controller;

import net.lovexq.seckill.common.model.JsonResult;
import net.lovexq.seckill.core.controller.BaseController;
import net.lovexq.seckill.kernel.service.EstateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 房产控制层
 *
 * @author LuPindong
 * @time 2017-04-19 07:42
 */
@Controller
public class EstateController extends BaseController {

    @Autowired
    private EstateService estateService;

    @ResponseBody
    @PostMapping("/crawler")
    public JsonResult invokeCrawler(HttpServletRequest request) throws Exception {
        String baseUrl = request.getParameter("baseUrl");
        String region = request.getParameter("region");
        Integer curPage = Integer.valueOf(request.getParameter("curPage"));
        Integer totalPage = Integer.valueOf(request.getParameter("totalPage"));

        result = estateService.invokeCrawler(baseUrl, region, curPage, totalPage);
        return result;
    }

    @GetMapping("/estate")
    public String listUI() {
        return "/estate/listUI";
    }

    @ResponseBody
    @GetMapping("/estate/data")
    public JsonResult listData(HttpServletRequest request) throws Exception {
        Sort sort = new Sort(Sort.Direction.DESC, "totalPrice");
        pageable = buildPageRequest(request, sort);
        result.setData(estateService.findSaleList(pageable));
        return result;
    }

    @GetMapping("/estate/{id}")
    public String detailUI() {
        return "/estate/detailUI";
    }

    @ResponseBody
    @GetMapping("/estate/{id}/data")
    public JsonResult detailData(HttpServletRequest request) throws Exception {
        return result;
    }
}