package poly.edu.service;

import poly.edu.model.Item;
import poly.edu.util.DB;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Map<Integer, Item> map = new HashMap<>();

    @Override
    public Item add(Integer id) {
        Item dbItem = DB.items.get(id);
        if (dbItem == null) return null;
        Item cartItem = map.get(id);
        if (cartItem == null) {
            cartItem = new Item(dbItem.getId(), dbItem.getName(), dbItem.getPrice(), 1);
            map.put(id, cartItem);
        } else {
            cartItem.setQty(cartItem.getQty() + 1);
        }
        return cartItem;
    }

    @Override
    public void remove(Integer id) {
        map.remove(id);
    }

    @Override
    public Item update(Integer id, int qty) {
        Item item = map.get(id);
        if (item == null) return null;
        if (qty <= 0) {
            map.remove(id);
            return null;
        }
        item.setQty(qty);
        return item;
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Collection<Item> getItems() {
        return map.values();
    }

    @Override
    public int getCount() {
        return map.values().stream().mapToInt(Item::getQty).sum();
    }

    @Override
    public double getAmount() {
        return map.values().stream().mapToDouble(i -> i.getPrice() * i.getQty()).sum();
    }
}