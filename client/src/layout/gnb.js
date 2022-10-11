import { AppBar, CssBaseline, Link, Toolbar, Typography, useTheme } from "@mui/material";
import { makeStyles } from 'tss-react/mui';
import { Link as RouterLink } from "react-router-dom";
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
    fixedLink : {
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

    const sidemenus = routes[0].children.filter((ch)=>{
        return ch.visible;
    });

    return (
            <AppBar position="static">
                <CssBaseline />
                <Toolbar className={classes.navlinks}>
                    <Typography variant="h4" className={classes.logo} component={RouterLink} to="home">
                    Home {/* 이미지 컴포넌트 교체필요 */}
                    </Typography>
                {(sidemenus).map((sidemenu) => {
                    return <Link component={RouterLink} to={sidemenu.path} className={classes.link} key={sidemenu.path}>
                    {sidemenu.name}
                    </Link>
                })}
                <Link to="로그인" className={classes.fixedLink} key="login">
                로그인
                </Link>
                <Link to="회원가입" className={classes.fixedLink} key="join">
                회원가입
                </Link>
                </Toolbar>
            </AppBar>
    )
}

export default Gnb;