import { useParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import api from '../../api/AxiosConfig';

const FlightDetails = () => {
    const { id } = useParams(); // default value is undefined?
    // const flightId = localStorage.getItem('flightId')
    const [flight, setFlight] = useState();
    // const params = useParams();

    useEffect(() => {
        const fetchFlight = async () => {
            try {
                const response = await api.get(`/flights/` + id);
                setFlight(response.data);
                console.log(response.data);
            } catch (error) {
                console.error('Failed to fetch flight details', error);
            }
        };

        fetchFlight();
    }, []);


    // if (!flight) return <p>Loading flight details...</p>;

    return (
        <div>
            <h2>Flight Details</h2>
            <p><strong>From:</strong> {flight.origin.city}</p>
            <p><strong>To:</strong> {flight.destination.city}</p>
            <p><strong>Departure time:</strong> {new Date(flight.departureTime).toLocaleString()}</p>
            <p><strong>Price:</strong> €{flight.price}</p>
            {/* Add more fields as needed */}
        </div>
    );
};

export default FlightDetails;
