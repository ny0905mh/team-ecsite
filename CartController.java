package jp.co.internous.team2411.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import jp.co.internous.team2411.model.domain.TblCart;
import jp.co.internous.team2411.model.domain.dto.CartDto;
import jp.co.internous.team2411.model.form.CartForm;
import jp.co.internous.team2411.model.mapper.TblCartMapper;
import jp.co.internous.team2411.model.session.LoginSession;


/**
 * カート情報に関する処理のコントローラー
 * @author インターノウス
 *
 */
@Controller
@RequestMapping("/team2411/cart")
public class CartController {
	@Autowired
	private LoginSession loginSession;
	
	@Autowired
	private TblCartMapper cartMapper;
	
	private Gson gson = new Gson();
	
	/**
	 * カート画面を初期表示する。
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/")
	public String index(Model m) {
		int userId;
		if (loginSession.isLogined()) {
			userId = loginSession.getUserId();
		} else {
			userId = loginSession.getTmpUserId();
		}
		
		List<CartDto> carts = cartMapper.findByUserId(userId);
		
		m.addAttribute("carts", carts);
		m.addAttribute("loginSession", loginSession);
		
		return "cart";
	}

	/**
	 * カートに追加処理を行う
	 * @param f カート情報のForm
	 * @param m 画面表示用オブジェクト
	 * @return カート画面
	 */
	@RequestMapping("/add")
	public String addCart(CartForm f, Model m) {
		int userId;
		if (loginSession.isLogined()) {
			userId = loginSession.getUserId();
		} else {
			userId = loginSession.getTmpUserId();
		}
		int productId = f.getProductId();
		int previousProductCount = cartMapper.findCountByUserIdAndProductId(userId, productId);
		
		TblCart cart = new TblCart(userId, productId, previousProductCount);
		
		cart.setProductCount(previousProductCount + f.getProductCount());
		
		if (previousProductCount != 0) {
			cartMapper.update(cart);
		} else {
			cartMapper.insert(cart);
		}
		
		List<CartDto> carts = cartMapper.findByUserId(userId);
		
		m.addAttribute("carts", carts);
		m.addAttribute("loginSession", loginSession);
		
		return "cart";
	}

	/**
	 * カート情報を削除する
	 * @param checkedIdList 選択したカート情報のIDリスト
	 * @return true:削除成功、false:削除失敗
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/delete")
	@ResponseBody
	public boolean deleteCart(@RequestBody String checkedIdList) {
		Map<String, Object> map = gson.fromJson(checkedIdList, Map.class);

		List<Integer> checkedIds = (List<Integer>) map.get("checkedIdList");
		
		int checkedCount = checkedIds.size();
		int deleteCount = 0;
		
		if (checkedCount > 0) {
			deleteCount = cartMapper.deleteById(checkedIds);
		}
		
		return deleteCount == checkedCount;
	}
}
