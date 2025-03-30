import { useParams } from 'react-router-dom';
import React, { useEffect, useState } from 'react';
import api from '../../api/AxiosConfig';
import FlightSeats from "./FlightSeats";

const FlightDetails = () => {
    const { id } = useParams();
    const [flight, setFlight] = useState(null);

    useEffect(() => {
        const fetchFlight = async () => {
            try {
                const response = await api.get(`/flights/flight/${id}`);
                setFlight(response.data);
            } catch (error) {
                console.error(error);
            }
        };

        if (id) {
            fetchFlight();
        }
    }, [id]);
    
    if (!flight) return <p>Loading flight details...</p>;

    return (
        <div>
            <h2>Flight Details</h2>
            <p><strong>From:</strong> {flight.origin.city}</p>
            <p><strong>To:</strong> {flight.destination.city}</p>
            <p><strong>Departure time:</strong> {new Date(flight.departureTime).toLocaleString()}</p>
            <p><strong>Price:</strong> €{flight.price}</p>
            <div>
                {/*<FlightSeats flight={flight}/>*/}
                {/*(Seat layout goes here)*/}
            </div>
        </div>
    );
};

export default FlightDetails;
