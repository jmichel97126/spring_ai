import type Customer_1 from "./Customer.js";
import type HotelBookingStatus_1 from "./HotelBookingStatus.js";
import type RoomType_1 from "./RoomType.js";
interface HotelBooking {
    bookingNumber?: string;
    checkInDate?: string;
    checkOutDate?: string;
    customer?: Customer_1;
    hotelName?: string;
    roomType?: RoomType_1;
    numberOfGuests: number;
    bookingStatus?: HotelBookingStatus_1;
}
export default HotelBooking;
