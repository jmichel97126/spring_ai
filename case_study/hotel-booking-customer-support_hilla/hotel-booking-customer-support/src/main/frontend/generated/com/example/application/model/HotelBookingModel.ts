import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import CustomerModel_1 from "./CustomerModel.js";
import type HotelBooking_1 from "./HotelBooking.js";
import HotelBookingStatusModel_1 from "./HotelBookingStatusModel.js";
import RoomTypeModel_1 from "./RoomTypeModel.js";
class HotelBookingModel<T extends HotelBooking_1 = HotelBooking_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(HotelBookingModel);
    get bookingNumber(): StringModel_1 {
        return this[_getPropertyModel_1]("bookingNumber", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get checkInDate(): StringModel_1 {
        return this[_getPropertyModel_1]("checkInDate", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.time.LocalDate" } }));
    }
    get checkOutDate(): StringModel_1 {
        return this[_getPropertyModel_1]("checkOutDate", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.time.LocalDate" } }));
    }
    get customer(): CustomerModel_1 {
        return this[_getPropertyModel_1]("customer", (parent, key) => new CustomerModel_1(parent, key, true));
    }
    get hotelName(): StringModel_1 {
        return this[_getPropertyModel_1]("hotelName", (parent, key) => new StringModel_1(parent, key, true, { meta: { javaType: "java.lang.String" } }));
    }
    get roomType(): RoomTypeModel_1 {
        return this[_getPropertyModel_1]("roomType", (parent, key) => new RoomTypeModel_1(parent, key, true));
    }
    get numberOfGuests(): NumberModel_1 {
        return this[_getPropertyModel_1]("numberOfGuests", (parent, key) => new NumberModel_1(parent, key, false, { meta: { javaType: "int" } }));
    }
    get bookingStatus(): HotelBookingStatusModel_1 {
        return this[_getPropertyModel_1]("bookingStatus", (parent, key) => new HotelBookingStatusModel_1(parent, key, true));
    }
}
export default HotelBookingModel;
