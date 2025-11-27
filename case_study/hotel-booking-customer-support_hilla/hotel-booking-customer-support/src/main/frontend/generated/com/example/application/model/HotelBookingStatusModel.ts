import { _enum as _enum_1, EnumModel as EnumModel_1, makeEnumEmptyValueCreator as makeEnumEmptyValueCreator_1 } from "@vaadin/hilla-lit-form";
import HotelBookingStatus_1 from "./HotelBookingStatus.js";
class HotelBookingStatusModel extends EnumModel_1<typeof HotelBookingStatus_1> {
    static override createEmptyValue = makeEnumEmptyValueCreator_1(HotelBookingStatusModel);
    readonly [_enum_1] = HotelBookingStatus_1;
}
export default HotelBookingStatusModel;
