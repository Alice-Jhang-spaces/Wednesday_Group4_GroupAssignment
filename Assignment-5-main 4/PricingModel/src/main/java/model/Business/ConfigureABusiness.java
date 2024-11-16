/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Business;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;
import java.util.Collections;
import java.util.HashMap;

import model.CustomerManagement.ChannelCatalog;
import model.CustomerManagement.CustomerDirectory;
import model.CustomerManagement.CustomerProfile;
import model.CustomerManagement.MarketCatalog;
import model.MarketModel.Market;
import model.MarketModel.MarketChannelAssignment;
import model.MarketModel.MasterMCAList;
import model.MarketModel.Channel;
import model.OrderManagement.MasterOrderList;
import model.OrderManagement.Order;
import model.Personnel.Person;
import model.Personnel.PersonDirectory;
import model.ProductManagement.Product;
import model.ProductManagement.ProductCatalog;
import model.ProductManagement.SolutionOffer;
import model.ProductManagement.SolutionOfferCatalog;
import model.Supplier.Supplier;
import model.Supplier.SupplierDirectory;

/**
 *
 * @author kal bugrara
 */
public class ConfigureABusiness {
  static int upperPriceLimit = 1000;
  static int lowerPriceLimit = 50;
  //static int range = 5;
  static int productMaxQuantity = 11;
  static int supplierCount = 5;
  static int customerCount = 5;

  public static Business createABusinessAndLoadALotOfData(String name, int supplierCount,int customerCount) {
    Business business = new Business(name);

    // Add Suppliers +
    loadSuppliers(business, supplierCount);

    // Add Products +
    loadProducts(business);

    // Create Markets
    loadMarkets(business);

    // Add Channels
    loadChannels(business);

    // Give Channels to Market by Age Group
    giveChanneltoMarketByAgeGroup(business);

    solutionCatalog(business);

    // Add Customers
    loadCustomers(business, customerCount);

    // Distribute Customers to Markets
    distributeCustomersToMarkets(business);

    // Add Order
    loadOrders(business);

    return business;
  }
  public static void loadSuppliers(Business b, int supplierCount) {
    Faker faker = new Faker();

    SupplierDirectory supplierDirectory = b.getSupplierDirectory();
    for (int index = 1; index <= supplierCount; index++) {
      supplierDirectory.newSupplier(faker.company().name());
    }
  }

  static void loadProducts(Business b) {
    SupplierDirectory supplierDirectory = b.getSupplierDirectory();

    for (Supplier supplier : supplierDirectory.getSupplierList()) {

      int randomProductNumber = getRandom(10, 20);
      ProductCatalog productCatalog = supplier.getProductCatalog();

      for (int index = 1; index <= randomProductNumber; index++) {

        String productName = "Product #" + index + " from " + supplier.getName();

        int randomNumber = getRandom(lowerPriceLimit, upperPriceLimit);

        int range;
        if (randomNumber < 100) {
            range = 10; 
        } else if (randomNumber < 500) {
            range = 50; 
        } else {
            range = 100; 
        }
        
        int randomFloor = getRandom(randomNumber - range, randomNumber);
        int randomCeiling = getRandom(randomNumber, randomNumber + range);
        int randomTarget = getRandom(randomFloor, randomCeiling);
        int randomStock = getRandom(800,2000);

        productCatalog.newProduct(productName, randomFloor, randomCeiling, randomTarget,randomStock);
      }
    }
  }

  static void solutionCatalog(Business b) {
    createBundleSolutionOffers(b);
    passSelectedProductstoSolutionOffer(b);
  }

  static void createBundleSolutionOffers(Business b) {
    SolutionOfferCatalog soc = b.getSolutionOfferCatalog();
    SupplierDirectory sd = b.getSupplierDirectory();
    MasterMCAList mcal = b.mastermcalist;

    for (MarketChannelAssignment mca : mcal.getMarketChannelAssignmentList()) {

      // each Supplier has a bundle
      for (Supplier s : sd.getSupplierList()) {
        SolutionOffer newSolutionOffer = mca.generateSolutionOffer(s);
        soc.addSolutionOffer(newSolutionOffer);
      }
    }
  }

  static void passSelectedProductstoSolutionOffer(Business b) {
    SolutionOfferCatalog soc = b.getSolutionOfferCatalog();
    ArrayList<SolutionOffer> togo = new ArrayList<>();

    for (SolutionOffer so : soc.getSolutionOfferList()) {
      Supplier s = so.getSupplier();
      ArrayList<Product> bundle = pickRandomProductsAsBundle(b, s);
      // so.setBundle(bundle);

      for (Product p : bundle) {
        so.addProduct(p);
      }
    }

    soc.getSolutionOfferList().addAll(togo);
  }

  static ArrayList<Product> pickRandomProductsAsBundle(Business b, Supplier s) {
        ArrayList<Product> bundle = new ArrayList<>();
        SupplierDirectory sd = b.getSupplierDirectory();

        List<Product> allProductsWithStock = new ArrayList<>();
        for (Supplier supplier : sd.getSupplierList()) {
            for (Product product : supplier.getProductCatalog().getProductList()) {
                if (product.getStockQuantity() > 0) {
                    allProductsWithStock.add(product);
                }
            }
        }

        if (allProductsWithStock.isEmpty()) {
            return bundle;
        }

        Collections.shuffle(allProductsWithStock); 
        int randomProductNumber = getRandom(2, Math.min(allProductsWithStock.size(), 10)); 
        for (int i = 0; i < randomProductNumber; i++) {
            bundle.add(allProductsWithStock.get(i));
        }

        return bundle;
  }

    static int getRandom(int lower, int upper) {
      Random r = new Random();
      int range = upper - lower + 1; 
      if (range <= 0) {
          throw new IllegalArgumentException("Upper bound must be greater than lower bound.");
      }
      return lower + r.nextInt(range);
    }

    static String getRandomContinent() {
      String[] continents = { "Asia", "Africa", "North America", "South America", "Europe", "Australia" };
      Random r = new Random();
      int randomInt = r.nextInt(continents.length);
      return continents[randomInt];
    }

  static String getRandomGender() {
    String[] gender = { "Male", "Female", "Non-Binary" };
    Random r = new Random();
    int randomInt = r.nextInt(gender.length);
    return gender[randomInt];
  }

  static String getRandomAgeGroup() {
    String[] ageGroup = { "Youth", "Middle-Aged", "Elderly" };
    Random r = new Random();
    int randomInt = r.nextInt(ageGroup.length);
    return ageGroup[randomInt];
  }

  static void loadMarkets(Business b) {
    MarketCatalog mc = b.getMarketCatalog();

    List<String> continentList = new ArrayList<>(
        Arrays.asList("Asia", "Africa", "North America", "South America", "Europe", "Australia"));

    for (String cl : continentList) {
      Market continentMarket = new Market(cl);

      List<String> genderList = new ArrayList<>(Arrays.asList("Male", "Female", "Non-Binary"));

      for (String gl : genderList) {
        Market genderMarket = continentMarket.newSubMarket(gl);

        List<String> ageList = new ArrayList<>(Arrays.asList("Youth", "Middle-Aged", "Elderly"));

        for (String al : ageList) {

          Market ageGroupMarket = genderMarket.newSubMarket(al);
          mc.addMarkettoCatalog(ageGroupMarket);
        }

      }
    }
  }

  static void loadChannels(Business b) {
    ChannelCatalog cc = b.getChannelCatalog();
    cc.newChannel("Internet");
    cc.newChannel("Radio");
    cc.newChannel("TV");
  }

  static void giveChanneltoMarketByAgeGroup(Business b) {
    MarketCatalog mc = b.getMarketCatalog();
    ChannelCatalog cc = b.getChannelCatalog();
    MasterMCAList mcal = b.mastermcalist;

    for (Market m : mc.getMarketList()) {
      for (Channel c : cc.getChannelList()) {
        ArrayList<String> marketCharacteristic = m.getCharacteristics();
        String channelType = c.getChannelType();
        if (marketCharacteristic.contains("Youth") && channelType.equals("Internet")) {
          m.newMarketChannelAssignment(c);
          c.addMarketChannelAssignment(m);
          mcal.addMarketChannelAssignment(m, c);
        }

        else if (marketCharacteristic.contains("Elderly") && channelType.equals("Radio")) {
          m.newMarketChannelAssignment(c);
          c.addMarketChannelAssignment(m);
          mcal.addMarketChannelAssignment(m, c);
        }

        else if (marketCharacteristic.contains("Middle-Aged") && channelType.equals("TV")) {
          m.newMarketChannelAssignment(c);
          c.addMarketChannelAssignment(m);
          mcal.addMarketChannelAssignment(m, c);
        }

      }
    }
  }

  static void loadCustomers(Business b, int customerCount) {
    CustomerDirectory customerDirectory = b.getCustomerDirectory();
    PersonDirectory personDirectory = b.getPersonDirectory();

    Faker faker = new Faker();

    for (int index = 1; index <= customerCount; index++) {

      String randomName = faker.name().fullName();

      String randomContinent = getRandomContinent();

      String randomGender = getRandomGender();

      String randomAge = getRandomAgeGroup();

      Person newPerson = personDirectory.newPerson(randomName, randomContinent, randomGender, randomAge);
      customerDirectory.newCustomerProfile(newPerson);
    }
  }

  static void distributeCustomersToMarkets(Business b) {
    ArrayList<CustomerProfile> customers = b.getCustomerDirectory().getCustomerList();
    ArrayList<Market> marketList = b.getMarketCatalog().getMarketList();

    for (CustomerProfile cp : customers) {
      for (Market market : marketList) {
        ArrayList<String> characteristics = market.getCharacteristics();
        if(characteristics.contains(cp.getCustomerContinent()) 
            && characteristics.contains(cp.getCustomerGender()) 
            && characteristics.contains(cp.getCustomerAge()) ) {

              
              cp.setMarket(market);
              market.addCustomerProfile(cp);
        }
      }
    }
  }

    static void loadOrders(Business b) {
      MasterOrderList mol = b.getMasterOrderList();
      CustomerDirectory cd = b.getCustomerDirectory();
      SolutionOfferCatalog soc = b.getSolutionOfferCatalog();

      HashMap<Product, Integer> productOrderedQuantities = new HashMap<>();

      for (CustomerProfile cp : cd.getCustomerList()) {
          Order orderForEach = mol.newOrder(cp);
          int numberOfOrders = getRandom(1, 10); 

          for (int i = 0; i < numberOfOrders; i++) {
              SolutionOffer so = soc.pickRandomSolutionOffer(); 
              ArrayList<Product> randomSolution = so.getProducts();

              for (Product p : randomSolution) {
                  if (!productOrderedQuantities.containsKey(p)) {
                      productOrderedQuantities.put(p, 0);
                  }

                  int productStock = p.getStockQuantity();
                  int existingOrderedQuantity = productOrderedQuantities.get(p);
                  if (existingOrderedQuantity < productStock) {
                      int orderQuantity = Math.min(getRandom(1, productMaxQuantity), productStock - existingOrderedQuantity);
                      int expandedFloorPrice = (int)(p.getFloorPrice()); 
                      int expandedCeilingPrice = (int)(p.getCeilingPrice()); 
                      int randomActualPrice = getRandom(expandedFloorPrice, expandedCeilingPrice);
                      orderForEach.newOrderItem(p, randomActualPrice, orderQuantity);
                      productOrderedQuantities.put(p, existingOrderedQuantity + orderQuantity);
                  }
              }
          }
      }

  }


  
  
}
