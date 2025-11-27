import { _getPropertyModel as _getPropertyModel_1, makeObjectEmptyValueCreator as makeObjectEmptyValueCreator_1, NumberModel as NumberModel_1, ObjectModel as ObjectModel_1, StringModel as StringModel_1 } from "@vaadin/hilla-lit-form";
import HotelBookingStatusModel_1 from "../model/HotelBookingStatusModel.js";
import RoomTypeModel_1 from "../model/RoomTypeModel.js";
import type HotelBookingDetails_1 from "./HotelBookingDetails.js";
class HotelBookingDetailsModel<T extends HotelBookingDetails_1 = HotelBookingDetails_1> extends ObjectModel_1<T> {
    static override createEmptyValue = makeObjectEmptyValueCreator_1(HotelBookingDetailsModel);
    get bookingNumber(): StringModel_1 {
        return this[_getPropertyModel_1]("bookingNumber", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.lang.String" } }));
    }
    get firstName(): StringModel_1 {
        return this[_getPropertyModel_1]("firstName", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.lang.String" } }));
    }
    get lastName(): StringModel_1 {
        return this[_getPropertyModel_1]("lastName", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.lang.String" } }));
    }
    get checkInDate(): StringModel_1 {
        return this[_getPropertyModel_1]("checkInDate", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.time.LocalDate" } }));
    }
    get checkOutDate(): StringModel_1 {
        return this[_getPropertyModel_1]("checkOutDate", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.time.LocalDate" } }));
    }
    get bookingStatus(): HotelBookingStatusModel_1 {
        return this[_getPropertyModel_1]("bookingStatus", (parent, key) => new HotelBookingStatusModel_1(parent, key, false));
    }
    get hotelName(): StringModel_1 {
        return this[_getPropertyModel_1]("hotelName", (parent, key) => new StringModel_1(parent, key, false, { meta: { javaType: "java.lang.String" } }));
    }
    get roomType(): RoomTypeModel_1 {
        return this[_getPropertyModel_1]("roomType", (parent, key) => new RoomTypeModel_1(parent, key, false));
    }
    get numberOfGuests(): NumberModel_1 {
        return this[_getPropertyModel_1]("numberOfGuests", (parent, key) => new NumberModel_1(parent, key, false, { meta: { javaType: "int" } }));
    }
}
export default HotelBookingDetailsModel;
