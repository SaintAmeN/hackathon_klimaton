import React, {useEffect, useState} from 'react';
import CardComponent from "../../CardComponent";
import instance from "../../../axios/axios";
import TableComponent from "../../TableComponent";

const TABLE_CONTENT_HEADERS = {
    "id": "ID",
    "name": "Name",
    "priceRange": "Price Range"
}

const ContentAchievements = () => {
    const [personalAchievements, setPersonalAchievements] = useState([]);

    useEffect(() => {
        fetchAchievements();

        return () => {
        }
    }, [])

    const fetchAchievements = () => {
        instance.get("/processing/achievements")
            .then((data) => {
                // data ma pole data
                console.log("Rekordy: " + JSON.stringify(data.data));
            })
            .catch((error) => {
                console.log("Otrzymaliśmy odpowiedź o błędzie!")
                console.log(error)
            });
    }

    return (
        <div>
            <CardComponent title={'Achievements'}>
                <TableComponent dataHeaders={TABLE_CONTENT_HEADERS} dataRows={personalAchievements}/>
            </CardComponent>
        </div>
    )
}

export default ContentAchievements;