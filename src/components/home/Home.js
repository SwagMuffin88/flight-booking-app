import React from "react";
import FlightsTable from "../flights/FlightsTable";

const Home = ({flights}) => {
    return (
      <div>
        <FlightsTable flights={flights}/>
      </div>
    )
}

export default Home