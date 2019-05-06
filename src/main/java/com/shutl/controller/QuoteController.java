package com.shutl.controller;

import com.shutl.model.Quote;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class QuoteController {

    @RequestMapping(value = "/quote", method = POST)
    public @ResponseBody Quote quote(@RequestBody Quote quote) {
        Long price = Math.abs((Long.valueOf(quote.getDeliveryPostcode(), 36) - Long.valueOf(quote.getPickupPostcode(), 36))/100000000);

        Map<String, Double> multiplier = new HashMap<String, Double>() {{
            put("bicycle", 1.1);
            put("motorbike", 1.15);
            put("parcel_car", 1.2);
            put("small_van", 1.3);
            put("large_van", 1.4);
        }};

        return new Quote(quote.getPickupPostcode(), quote.getDeliveryPostcode(), quote.getVehicle (), Math.round(price * multiplier.get(quote.getVehicle())));
    }
}
