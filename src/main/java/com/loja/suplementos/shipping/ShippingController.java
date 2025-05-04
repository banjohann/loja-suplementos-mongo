package com.loja.suplementos.shipping;

import com.loja.suplementos.shipping.domain.Shipping;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/shipping")
public class ShippingController {

     private final ShippingService shippingService;

     @GetMapping
     public String index(Model model) {
         model.addAttribute("shippings", shippingService.findAll());
         return "shipping/index";
     }

     @GetMapping("/{id}")
     public String detail(@PathVariable Long id, Model model) {
         model.addAttribute("shipping", shippingService.findById(id));
         return "shipping/details";
     }

     @PostMapping()
     public String saveShipping(Shipping shipping) {
         shippingService.save(shipping);
         return "redirect:/shipping";
     }

     @GetMapping("/new")
     public String newShippingForm(Model model) {
         model.addAttribute("shipping", new Shipping());
         return "shipping/new";
     }

     @GetMapping("/edit/{id}")
     public String editShippingForm(@PathVariable Long id, Model model) {
         model.addAttribute("shipping", shippingService.findById(id));
         return "shipping/edit";
     }

     @PostMapping("/edit/{id}")
     public String updateShipping(@PathVariable Long id, Shipping shipping) {
         shippingService.update(id, shipping);
         return "redirect:/shipping";
     }

     @PostMapping("/delete/{id}")
     public ResponseEntity<?> deleteShipping(@PathVariable Long id) {
         shippingService.delete(id);
         return ResponseEntity.ok().build();
     }
}
