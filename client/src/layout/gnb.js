import {
  AppBar,
  CssBaseline,
  Link,
  Toolbar,
  Typography,
  useTheme,
} from "@mui/material";
import { makeStyles } from "tss-react/mui";
import { Link as RouterLink, useLocation } from "react-router-dom";
import routes from "../configs/rootRoutes";

const useStyles = makeStyles()((theme) => ({
  navlinks: {
    display: "flex",
    justifyContent: "space-between",
    backgroundColor: "white",
  },
  logo: {
    textDecoration: "none",
    flexGrow: "0.5",
    cursor: "pointer",
    color: "black",
  },
  fixedLink: {
    textDecoration: "none",
    color: "black",
    fontSize: "15px",
    marginLeft: theme.spacing(5),
  },
  link: {
    textDecoration: "none",
    color: "black",
    fontSize: "20px",
    marginRight: theme.spacing(20),
    "&:hover": {
      color: "#ccddcc",
      borderBottom: "1px solid white",
    },
  },
}));

const Gnb = () => {
  const theme = useTheme();
  const { classes } = useStyles(theme);

  const loc = useLocation();
  const sidemenus = routes[0].children.filter((ch) => {
    return ch.visible;
  });

  return (
    <AppBar position="static">
      <CssBaseline />
      <Toolbar className={classes.navlinks}>
        <Typography
          variant="h4"
          className={classes.logo}
          component={RouterLink}
          to="/"
        >
          부캠도감
        </Typography>
        {sidemenus.map((sidemenu) => {
          console.log(sidemenu);
          return (
            <Link
              component={RouterLink}
              to={sidemenu.path}
              className={classes.link}
            >
              {sidemenu.name}
            </Link>
          );
        })}
        {/* <Link to="/login">로그인</Link>
        <Link to="/register">회원가입</Link> */}
        <Typography
          variant="h10"
          className={classes.logo}
          component={RouterLink}
          to="/login"
        >
          로그인
        </Typography>
        <Typography
          variant="h10"
          className={classes.logo}
          component={RouterLink}
          to="/register"
        >
          회원가입
        </Typography>
      </Toolbar>
    </AppBar>
  );
  {
    /*<List>
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
            </List>*/
  }
  /*return (
        <div style={{"borderBottom":"1px solid #999"}}>
            <h3>React + MUI + ReduxToolkit</h3>
        </div>
    )*/
};

export default Gnb;
