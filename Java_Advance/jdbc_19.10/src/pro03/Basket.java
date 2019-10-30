package pro03;

import java.util.ArrayList;
import java.util.Collections;

public class Basket {
	ArrayList<ShopItem> selectedItems;

	public Basket() {
		super();
		selectedItems = new ArrayList<>();
	}

	public Basket(ArrayList<ShopItem> selectedItems) {
		super();
		this.selectedItems = selectedItems;
	}

	public boolean addItem(ShopItem item, int iQtt) throws CloneNotSupportedException {
		if (item.getiQttt() >= iQtt) {
			item.setiQttt(item.getiQttt() - iQtt);

			ShopItem shopClone = (ShopItem) item.clone();
			shopClone.setiQttt(iQtt);

			System.out.println(item);
			System.out.println(shopClone);
			return selectedItems.add(shopClone);
		}

		return false;
	}

	public void displayItemBasket() {
		if (selectedItems.isEmpty()) {
			System.out.println("basket is empty");
		}

		Collections.sort(selectedItems);

		selectedItems.forEach(item -> item.display());
	}

	public int getNumberSofware() {
		int number = 0;
		for (ShopItem item : selectedItems) {
			if (item instanceof Software) {
				number += item.getiQttt();
			}
		}

		return number;
	}

	ArrayList<ShopItem> getSelectedItems() {
		return selectedItems;
	}

	public double getTotalPrice() {
		return getTotalPriceOfBook() + getTotalPriceOfSoftware();
	}

	public double getTotalPriceOfBook() {
		double total = 0;
		long totalWeightBook = getTotalWeightBook();

		// calculate price item
		for (ShopItem item : selectedItems) {
			if (item instanceof Book) {
				total += (item.getiPrice() * item.getiQttt());
			}
		}

		// ship price
		long numberKG = (totalWeightBook / 1000);
		totalWeightBook -= (numberKG * 1000);

		total += (numberKG * 7);
		if (totalWeightBook > 0 && totalWeightBook < 500) {
			total += 5;
		} else if (totalWeightBook > 0 && totalWeightBook < 1000)
			total += 9.5;

		return total;
	}

	public double getTotalPriceOfSoftware() {
		double totalShipPrice = 0, totalPrice = 0;

		for (ShopItem item : selectedItems) {
			if (item instanceof Software) {
				totalShipPrice += (item.getPriceShip() * item.getiQttt());
				totalPrice += (item.getiPrice() * item.getiQttt());
			}
		}

		return totalShipPrice + totalPrice;
	}

	public long getTotalWeightBook() {
		long total = 0;

		for (ShopItem item : selectedItems) {
			if (item instanceof Book) {
				total += (((Book) item).getWeight() * item.getiQttt());
			}
		}

		return total;
	}

	public boolean removeItem(ShopItem item, int iqtt) {
		if (item.getiQttt() >= iqtt) {
			if (item.getiQttt() == iqtt)
				selectedItems.remove(item);
			else {
				int index = Validate.findIdFromStore(selectedItems, item.getId());
				item.setiQttt(item.getiQttt() - iqtt);
				selectedItems.set(index, item);
			}

			return true;
		}
		return false;
	}

	void setSelectedItems(ArrayList<ShopItem> selectedItems) {
		this.selectedItems = selectedItems;
	}

	@Override
	public String toString() {
		return "Basket [selectedItems=" + selectedItems + "]";
	}

}
