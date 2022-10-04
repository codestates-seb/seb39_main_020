import {  Icon, List, ListItem, ListItemButton } from "@mui/material";
import { Link as RouterLink, useLocation } from "react-router-dom"
import routes from "../configs/rootRoutes";

const Lnb = () => {
    const loc = useLocation();
    const sidemenus = routes.find((r) => {
        return r.path === '/';
    })?.children.filter((ch)=>{
        return ch.visible
    });

    return (
        <div>
            <List>
                {(sidemenus).map((sidemenu) => {
                    return (sidemenu.path) && 
                        <ListItem key={sidemenu.path} disablePadding>
                            <ListItemButton 
                                component={RouterLink} 
                                to={sidemenu.path} 
                                selected={loc.pathname.indexOf(sidemenu.path) > -1}
                            >
                                {sidemenu.icon && (<Icon style={{fontSize: 15, marginRight:'.8em'}}>{sidemenu.icon}</Icon>)}
                                {sidemenu.name}
                            </ListItemButton>
                        </ListItem>
                })}
            </List>
        </div>
    )
}

export default Lnb;