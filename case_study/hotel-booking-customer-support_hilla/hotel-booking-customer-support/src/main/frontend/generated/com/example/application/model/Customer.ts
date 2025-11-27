import type HotelBooking_1 from "./HotelBooking.js";
interface Customer {
    firstName?: string;
    lastName?: string;
    bookings?: Array<HotelBooking_1 | undefined>;
}
export default Customer;
