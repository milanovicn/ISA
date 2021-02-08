export class MedicineReservation{
    id:number;
    pharmacyId:number;
    medicineId:number;
    patientId:number;
    medicineName:string;
    pharmacyName:string;
    pickUpDate:Date;
    status:string;
    patientEmail:string;
    isPickedUp:boolean;
}