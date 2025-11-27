import { _getPropertyModel as _getPropertyModel_1, ArrayModel as ArrayModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import type Customer_1 from "./Customer.js";
import HotelBookingModel_1 from "./HotelBookingModel.js";
class CustomerModel<T extends Customer_1 = Customer_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(CustomerModel);
    get firstName(): StringModel_1 {
        return this[_getPropertyModel_1]("firstName", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get lastName(): StringModel_1 {
        return this[_getPropertyModel_1]("lastName", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get bookings(): ArrayModel_1<HotelBookingModel_1> {
        return this[_getPropertyModel_1]("bookings", (parent, key) => new ArrayModel_1(parent, key, true, (parent, key) => new HotelBookingModel_1(parent, key, true), { meta: { javaType: "java.util.List" } }));
    }
}
export default CustomerModel;
