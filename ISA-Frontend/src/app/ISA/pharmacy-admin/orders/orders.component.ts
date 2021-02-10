import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { Medicine } from "app/ISA/shared/model/Medicine";
import { OrderItem } from "app/ISA/shared/model/OrderItem";
import { Orders } from "app/ISA/shared/model/Orders";
import { Pharmacy } from "app/ISA/shared/model/Pharmacy";
import { User } from "app/ISA/shared/model/User";
import { LoginService } from "app/ISA/shared/service/login.service";
import { MedicineService } from "app/ISA/shared/service/medicine.service";
import { PharmacyAdminService } from "app/ISA/shared/service/pharmacy-admin.service";

@Component({

  templateUrl: './orders.component.html'

})

export class OrdersComponent implements OnInit {
  ret:Object=new Object();
  activeOrders: Orders[] = [];
  allOrders: Orders[] = [];
  allMedicines: Medicine[] = [];
  newOrderItems: OrderItem[] = [];
  newOrder: Orders;
  newItem: OrderItem;
  user: User;
  myPharmacy: Pharmacy;
  filteredOffers: Orders[] = [];
  filterValue:string = "";


  constructor(private route: ActivatedRoute, private router: Router, private loginService: LoginService,
    private pharmacyAdminService: PharmacyAdminService, private medicineService: MedicineService) {
    this.user = new User();
    this.myPharmacy = new Pharmacy();
    this.newOrder = new Orders();
    this.newItem = new OrderItem();
   
    

  }

  ngOnInit(): void {
    this.pharmacyAdminService.getAllOrders(this.myPharmacy.id).subscribe(
      allOrders => {
          console.log("proba");
        this.allOrders = allOrders;
        this.filteredOffers = allOrders;
      }
    );
    this.getUser();
    this.getAllMedicines();
 
   

  }
 
  filter(){
    this.filteredOffers = [];
    for(let i=0; i<this.allOrders.length; i++){
      if(this.allOrders[i].orderStatus==this.filterValue){
        this.filteredOffers.push(this.allOrders[i]);
      }
    }

  }
  getAllMedicines() {

    this.medicineService.getAllMedicines().subscribe({
      next: allMedicines => {
        this.allMedicines = allMedicines;

      }
    });
  }


  getOrders() {
    this.pharmacyAdminService.getOrders(this.myPharmacy.id).subscribe(
      activeOrders => {
        this.activeOrders = activeOrders;
      }
    );

  }

  getAllOrders() {
    this.pharmacyAdminService.getAllOrders(this.myPharmacy.id).subscribe(
      allOrders => {
        this.allOrders = allOrders;
        this.filteredOffers = allOrders;
      }
    );

  }


  getUser() {

    this.loginService.getLoggedInUser().subscribe({
      next: t => {
        this.user = t;

        console.log(this.user);
        this.getPharmacyById();

      }

    });

  }

  getPharmacyById() {
    this.pharmacyAdminService.getPharmacyByAdminId(this.user.id).subscribe({
      next: pharmacy => {
        this.myPharmacy = pharmacy;
        this.getOrders();
        this.getAllOrders();


      }
    });

  }

  addItemToOrder() {
    console.log(this.newItem);
    for (let i = 0; i < this.allMedicines.length; i++) {
      if (this.allMedicines[i].id == this.newItem.medicineId) {
        this.newItem.medicineName = this.allMedicines[i].name;
      }
    }

    let toAdd = new OrderItem();
    toAdd.medicineId = this.newItem.medicineId;
    toAdd.medicineName = this.newItem.medicineName;
    toAdd.quantity = this.newItem.quantity;

    this.newOrderItems.push(toAdd);

  }

  createOrder() {
    this.newOrder.pharmacyAdminId = this.user.id;
    this.newOrder.pharmacyId = this.myPharmacy.id;
    this.newOrder.pharmacyName = this.myPharmacy.name;
    console.log(this.newOrder);

    this.pharmacyAdminService.createOrder(this.newOrder).subscribe({
      next: newOrder => {
        this.newOrder = newOrder;
        console.log(this.newOrder);

        for (let i = 0; i < this.newOrderItems.length; i++) {
            this.newOrderItems[i].orderId = this.newOrder.id;
        }
        this.createOrderItems();

      }
    });

  }

  createOrderItems() {
    this.pharmacyAdminService.createOrderItems(this.newOrderItems).subscribe({
      next: newOrder => {
        this.ret = newOrder;
        console.log(this.newOrder);

        if (this.newOrder == null) {
          alert("Order not created!");
        } else {
          alert("Order successfully created!");
        }
        this.refresh();
      }
    });

  }

  refresh() {
    window.location.reload();
  }
}