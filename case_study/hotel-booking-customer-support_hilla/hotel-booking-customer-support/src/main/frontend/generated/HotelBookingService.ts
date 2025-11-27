import { EndpointRequestInit as EndpointRequestInit_1 } from "@vaadin/hilla-frontend";
import type HotelBooking_1 from "./com/example/application/model/HotelBooking.js";
import type HotelBookingDetails_1 from "./com/example/application/services/HotelBookingDetails.js";
import client_1 from "./connect-client.default.js";
async function cancelBooking_1(bookingNumber: string, firstName: string, lastName: string, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("HotelBookingService", "cancelBooking", { bookingNumber, firstName, lastName }, init); }
async function getBookings_1(init?: EndpointRequestInit_1): Promise<Array<HotelBookingDetails_1>> { return client_1.call("HotelBookingService", "getBookings", {}, init); }
async function initData_1(init?: EndpointRequestInit_1): Promise<void> { return client_1.call("HotelBookingService", "initData", {}, init); }
async function roomTypeChangeRequest_1(bookingNumber: string, firstName: string, lastName: string, roomType: string, init?: EndpointRequestInit_1): Promise<void> { return client_1.call("HotelBookingService", "roomTypeChangeRequest", { bookingNumber, firstName, lastName, roomType }, init); }
async function toHotelBookingDetails_1(hotelBooking: HotelBooking_1, init?: EndpointRequestInit_1): Promise<HotelBookingDetails_1> { return client_1.call("HotelBookingService", "toHotelBookingDetails", { hotelBooking }, init); }
export { cancelBooking_1 as cancelBooking, getBookings_1 as getBookings, initData_1 as initData, roomTypeChangeRequest_1 as roomTypeChangeRequest, toHotelBookingDetails_1 as toHotelBookingDetails };
