import type HotelBookingStatus_1 from "../model/HotelBookingStatus.js";
import type RoomType_1 from "../model/RoomType.js";
interface HotelBookingDetails {
    bookingNumber: string;
    firstName: string;
    lastName: string;
    checkInDate: string;
    checkOutDate: string;
    bookingStatus: HotelBookingStatus_1;
    hotelName: string;
    roomType: RoomType_1;
    numberOfGuests: number;
}
export default HotelBookingDetails;
