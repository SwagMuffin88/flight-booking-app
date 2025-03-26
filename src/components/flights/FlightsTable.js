import React from "react";
import {Table, TableBody, TableCell, TableHead, TableRow} from "@mui/material";
import {useNavigate} from "react-router-dom";
// import FlightSeats from "./FlightSeats";

const FlightsTable = ({ flights }) => {
    if (!flights || flights.length === 0) return <p>No flights found.</p>;

    const navigate = useNavigate();

    const handleView = (flightId) => {
        // console.log("Flight id:" + flightId);
        navigate(`/flights/flight/${flightId}`);
    }

    return (
        <Table>
            <TableHead>
                <TableRow>
                    <TableCell>From</TableCell>
                    <TableCell>To</TableCell>
                    <TableCell>Departure time</TableCell>
                    <TableCell>Price</TableCell>
                    <TableCell></TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {flights.map(flight => (
                    <TableRow key={flight.id}>
                        <TableCell>{flight.origin.city} ({flight.origin.airportNameShort}) </TableCell>
                        <TableCell>{flight.destination.city} ({flight.destination.airportNameShort})</TableCell>
                        <TableCell>{new Date(flight.departureTime).toLocaleString()}</TableCell>
                        <TableCell>{flight.price}</TableCell>
                        <TableCell>
                            <button onClick={() => handleView(flight.id)}>View</button>
                        </TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
        
    );
};

export default FlightsTable